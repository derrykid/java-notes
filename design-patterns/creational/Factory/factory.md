- `Dialog.class` is creator
- `HtmlButton` and `WindowsButton` are product

---
**Structure** 
![Structure](https://refactoring.guru/images/patterns/diagrams/factory-method/structure-indexed.png) 


---
Another example from geekforgeeks
[Notification factory](https://www.geeksforgeeks.org/factory-method-design-pattern-in-java/) 

![notification diagram](https://media.geeksforgeeks.org/wp-content/uploads/20200427212325/Class-Diagram-12.png) 
There's a service that can notify user that there's a news by 3 ways: SMS, Email, and Push.

1. Make an interface `Notification` with `+notifyUser()`
2. `SMSNotification`, `EmailNotification`, `PushNotification` implementations, it can easily scale by simply add one more implementation.
3. Create a `NotificationService` or called *creator* class.
