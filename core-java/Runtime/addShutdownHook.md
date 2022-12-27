You only worry about shutdown hooks when you want something to happen when a shutdown occurs on the virtual machine.

You might have many situations like:

- your program had created many temporary files in filesystem you want to delete it;
- you need to send a distress signal to another process/machine before terminating;
- execute any clean-up actions, logging or after-error actions on unexpected behaviours.

```java
Runtime.getRuntime().addShutdownHook(new Thread(// runnable instance));
```
