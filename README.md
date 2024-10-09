# PiXLib
this is a simple minecraft plugin libary for making plugins easier to make

# How to use

**Your main class should look like this:**
```java
import com.pinont.piXLib.PiXPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // create plugin instance by
        PiXPlugin.setPlugin(this);
    }
    
    @Override
    public void onDisable() {
        // unregister plugin instance by
        PiXPlugin.unregister(this);
    }
}
```

**To register an event listener:** [auto registers is working in progress]
```java
import com.pinont.piXLib.PiXPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // create plugin instance by
        PiXPlugin.setPlugin(this);
        
        // register event listener by
        PiXPlugin.listeners.addAll(
            new EventListener(),
            new AnotherEventListener()
        );
    }
    
    @Override
    public void onDisable() {
        // unregister plugin instance by
        PiXPlugin.unregister(this);
    }
}
```

