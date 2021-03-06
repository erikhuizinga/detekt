package io.gitlab.arturbosch.detekt.core.reporting

import io.gitlab.arturbosch.detekt.api.Detektion
import io.gitlab.arturbosch.detekt.api.UnstableApi
import io.gitlab.arturbosch.detekt.api.getOrNull
import io.gitlab.arturbosch.detekt.api.internal.SimpleNotification
import io.gitlab.arturbosch.detekt.core.ProcessingSettings
import kotlin.system.measureTimeMillis

@OptIn(UnstableApi::class)
class OutputFacade(
    private val settings: ProcessingSettings
) {

    private var reports: Map<String, ReportPath>

    init {
        val reportPaths: Collection<ReportPath> =
            settings.getOrNull(DETEKT_OUTPUT_REPORT_PATHS_KEY) ?: emptyList()
        reports = reportPaths.associateBy { it.kind }
    }

    fun run(result: Detektion) {
        // Always run output reports first.
        // They produce notifications which may get printed on the console.
        handleOutputReports(result)
        handleConsoleReports(result)
    }

    private fun handleConsoleReports(result: Detektion) {
        val durationConsoleReports = measureTimeMillis {
            val extensions = ConsoleReportLocator(settings).load()
            extensions.forEach { it.print(settings.outPrinter, result) }
        }
        settings.debug { "Writing console results took $durationConsoleReports ms" }
    }

    private fun handleOutputReports(result: Detektion) {
        val durationOutputReports = measureTimeMillis {
            val extensions = OutputReportLocator(settings).load()
            for (report in extensions) {
                val filePath = reports[report.id]?.path
                if (filePath != null) {
                    report.write(filePath, result)
                    result.add(SimpleNotification("Successfully generated ${report.name} at $filePath"))
                }
            }
        }
        settings.debug { "Writing output results took $durationOutputReports ms" }
    }
}
