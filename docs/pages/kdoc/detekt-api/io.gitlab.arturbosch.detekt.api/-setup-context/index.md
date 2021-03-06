---
title: SetupContext - detekt-api
---

[detekt-api](../../index.html) / [io.gitlab.arturbosch.detekt.api](../index.html) / [SetupContext](./index.html)

# SetupContext

`interface SetupContext : `[`PropertiesAware`](../-properties-aware/index.html)

Context providing useful processing settings to initialize extensions.

### Properties

| [config](config.html) | Configuration which is used to setup detekt.`abstract val config: `[`Config`](../-config/index.html) |
| [configUris](config-uris.html) | All config locations which where used to create [config](config.html).`abstract val configUris: `[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<`[`URI`](https://docs.oracle.com/javase/8/docs/api/java/net/URI.html)`>` |
| [errPrinter](err-printer.html) | The channel to log all the errors.`abstract val errPrinter: `[`PrintStream`](https://docs.oracle.com/javase/8/docs/api/java/io/PrintStream.html) |
| [outPrinter](out-printer.html) | The channel to log all the output.`abstract val outPrinter: `[`PrintStream`](https://docs.oracle.com/javase/8/docs/api/java/io/PrintStream.html) |

### Extension Functions

| [getOrNull](../get-or-null.html) | Allows to retrieve stored properties in a type safe way.`fun <T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> `[`PropertiesAware`](../-properties-aware/index.html)`.getOrNull(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): T?` |

