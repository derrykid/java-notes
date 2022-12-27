## input field

- email
![](images/email.png)
```java
EmailField emailField = new EmailField();
emailField.setLabel("Your email address");
```

- text field
![](images/searchbar.png)
```java
TextField textField = new TextField();
textField.getElement().setAttribute("aria-label", "search");
textField.setPlaceholder("Search");
textField.setClearButtonVisible(true);
textField.setPrefixComponent(VaadinIcon.SEARCH.create());
add(textField);
```
