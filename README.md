# PiXLib
this is a simple minecraft plugin libary for making plugins easier to make

# How to use

**Your main class should look like this:**

```java
import com.pinont.experiences.Exp;
import com.pinont.experiences.PiXLib;
import com.pinont.experiences.PiXPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // register event listener by
        Exp.listeners.addAll(
                new EventListener(),
                new AnotherEventListener()
        );

        // initialize plugin instance by
        Exp.setPlugin(this);
    }

    @Override
    public void onDisable() {
        // unregister plugin instance by
        Exp.unregister(this);
    }
}
```

