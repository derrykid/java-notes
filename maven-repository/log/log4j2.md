[toc]

# Java Logging Components

Java logging API consists of three core components:
- `Loggers` are responsible for capturing events (called LogRecords) and passing them to the appropriate `Appender`.
- `Appenders` (called `Handlers` in some other frameworks) are responsible for recording log events to a destination. `Appenders` use `Layouts` to format events before sending them to an output(can be console or file).
- `Layouts`, in simple words, determine how the data looks when it appears in a log entry. `Layouts` (called `Formatters` in some other frameworks) are responsible for converting and formatting the data in a log event. 

> When application makes a logging call (e.g. `logger.error("Error!")`), the `Loggers` records the event and forwards it to appropriate `Appenders`. The Appender then formats the record using a `Layout` before sending it a destination (e.g. Console or file). 

Additionally we can have more than one `Filters` to specify which `Appenders` to use for which events. `Filters` isn't required, but can give us full control over the log.
