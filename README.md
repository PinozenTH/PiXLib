# PiXLib
this is a simple minecraft plugin libary for making plugins easier to make

# How to use

**Your main class should look like this:**

```java
import com.pinont.piXLib.PiXLib;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // create plugin instance by
        PiXLib.setPlugin(this);
    }

    @Override
    public void onDisable() {
        // unregister plugin instance by
        PiXLib.unregister(this);
    }
}
```

**To register an event listener:** [WIP: auto register]

```java
import com.pinont.piXLib.PiXLib;
import com.pinont.piXLib.PiXPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // register event listener by
        PiXLib.listeners.addAll(
                new EventListener(),
                new AnotherEventListener()
        );

        // initialize plugin instance by
        PiXLib.setPlugin(this);
    }

    @Override
    public void onDisable() {
        // unregister plugin instance by
        PiXLib.unregister(this);
    }
}
```

