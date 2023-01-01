[toc]

[Logback, Slf4j, Log4j2](https://www.youtube.com/watch?v=SWHYrCXIL38) 


## Point to grab

1. The most popular logging frameworks now are `Logback`, and `Log4j2`
2. `Slf4j` is a facade for logging frameworks. In short, it's an interface, you only have to use the `Slf4j` methods, and you can use different framework implementations you want.
3. Config file is located at `src/main/resources/{name}.xml`

## Setup
[Setups for different frameworks](https://www.baeldung.com/slf4j-with-log4j2-logback) 

### Log4j2 Setup
To use Slf4j with Log4j2
```xml
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.7</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.7</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j-impl</artifactId>
    <version>2.7</version>
</dependency>
```

### Logback Setup
```
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.6</version>
</dependency>
```

## Appenders, Layouts, Filters

Java logging API consists of three core components:    
- `Loggers` are responsible for capturing events (called LogRecords) and passing them to the appropriate `Appender`.
- `Appenders` (called `Handlers` in some other frameworks) are responsible for recording log events to a destination. `Appenders` use `Layouts` to format events before sending them to an output(can be console or file).
- `Layouts`, in simple words, determine how the data looks when it appears in a log entry. `Layouts` (called `Formatters` in some other frameworks) are responsible for converting and formatting the data in a log event. 
    
> When application makes a logging call (e.g. `logger.error("Error!")`), the `Loggers` records the event and forwards it to appropriate `Appenders`. The Appender then formats the record using a `Layout` before sending it a destination (e.g. Console or file). 
    
Additionally we can have more than one `Filters` to specify which `Appenders` to use for which events. `Filters` isn't required, but can give us full control over the log.

Default Config
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="ConsoleAppender" target="SYSTEM_OUT">
            <PatternLayout 
              pattern="%d [%t] %-5level %logger{36} - %msg%n%throwable"/>
        </Console>

        // add more appender between the tag

    </Appenders>
    <Loggers>
        <Root level="ERROR">
            <AppenderRef ref="ConsoleAppender"/> // point to the 'name'
            //if there more appender <AppenderRef ref="name"/>
        </Root>
    </Loggers>
</Configuration>
```

Common appenders:
- `<Console>` - print to console
- `<RollingFile>` - create a rolling log file
- `<Async>` - asynchronous log, while application performance is the priority
- `<Syslog>` - send log event to remote machine
- `<FiloverAppender>` - where one appender fails to process the log events and we do not want to lose the data.

### PatternLayout
`PatternLayout` formats the log pattern layout. It's between the individual appender's tag

1. Common use
```
<Console name="Console" target="SYSTEM_OUT">
    <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
</Console>

```

2. Custom color
```
<Console name="ConsoleAppender" target="SYSTEM_OUT">
    <PatternLayout pattern="%style{%date{DEFAULT}}{yellow}
      %highlight{%-5level}{FATAL=bg_red, ERROR=red, WARN=yellow, INFO=green} 
      %message"/>
</Console>
```

### RollingFile

Tj-bot `RollingFile` in `log4j2.xml`
```
<RollingFile name="File" fileName="logs/app.log"
             filePattern="logs/app-%d{dd-MM-yyyy}.log.gz"
             ignoreExceptions="false">
    <PatternLayout charset="UTF-8"
                   pattern="%d{yyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
    <Policies>
        <SizeBasedTriggeringPolicy size="10 MB"/>
    </Policies>
</RollingFile>
```

- `fileName` - saves the file to `log/app.log`
- `filePattern` - specify the save and archive file format
- `PatternLayout` - the layout in `app.log` file
- `Policies` - the triggering policy for `RollingFile` event occurs

[Triggering Policies](https://logging.apache.org/log4j/2.x/manual/appenders.html#TriggeringPolicies) 
- `<SizeBasedTriggeringPolicy size="20 MB">` - causes a rollover once the file has reached the specified size. 
- `<TimeBasedTriggeringPolicy />` -  causes a rollover once the date/time pattern no longer applies to the active file.
    - `interval` - how often a rollover should occur, every 4 hours? 3 hours?
    - `modulate` - Should it be adjusted? Like `interval="4" modulate="true"` means 4am, 8am, noon, etc. Default false.
    - `maxRandomDelay` - randomly delay a rollover.


### Async file appender
```
<Async name="AsyncAppender" bufferSize="80">
    <AppenderRef ref="JSONLogfileAppender"/>
</Async>
```
