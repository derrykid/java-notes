- [Nesting if statement](https://www.youtube.com/watch?v=ZzwWWut_ibU&t=3s) 

Commonly nested-if style
```
void function() {
    if (wifi) {
        if (login) {
            if (admin) {
                seeAdminPanel();
            } else {
                debugPrint('Must be admin');
            }
        } else {
            debugPrint('Must login');
        }
    } else {
        debugPrint('Must connected to WIFI');
    }
}
```

This actually bounces our eyes between each if else statement

Try to apply the **Guard Causes** style: separate condition and function, reverse the condition.

```
void function(){
    if (!wifi){
        debugPrint('Must connect');
        return;
    }
    if (!login){
        debutPrint('Must login');
        return;
    }
    if (!admin){
        debug('Must be admin');
        return;
    }

    // we can add more conditions with ease

    seeAdminPanel();
}
```
