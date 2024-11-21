# ! BIG CHANGE: PixLib is now renamed to Experience !

<img src="https://imgur.com/RTuYY8g.png" />

# Experience
A Minecraft framework designed to create a new experience for developing plugins from the ground up.

# How to use

Please follow the steps below to use the Experience framework in your plugin.

## Step 1: Add the Experience dependency

```xml
<!-- rest of the code ... <dependencies> -->

    <dependency>
        <groupId>com.pinont</groupId>
        <artifactId>experiences</artifactId>
        <version>Latest version of Experiences</version>
        <scope>compile</scope>
    </dependency>

<!-- rest of the code ... </dependencies> -->
```

## Step 2: extends ExpPlugin to your MainClass

```java
import com.pinont.experiences.plugin.ExpPlugin;

public class MainClass extends ExpPlugin {

    @Override
    public void onPluginStart() {
        this.addListener(new SomeEventListener());

        this.addCommand(new SomeCommand());
    }

    @Override
    public void onPluginStop() { /* Do something when the plugin is being disabled */ }

    public static MainClass getInstance() {
        return (MainClass) ExpPlugin.getPlugin();
    }
}
```

# Features (WIP)

* [ ] Automatic command registration
* [ ] Automatic event listener registration
* [ ] Automatic configuration file generation
* [ ] Automatic language file generation
* [ ] Automatic database connection
* [ ] Automatic plugin update checker
* [ ] Automatic plugin metrics
* [X] Simple method to create an Item using `ItemBuilder`
* [X] Simple method to create an Entity using `EntityBuilder` *(Without summoning the entity)* _*Experimental*_
* [X] Create a GUI using `Gui`
* [ ] Rework Economy System
* [X] Rework Scoreboard System _*Experimental*_
* [X] Rework Messaging Method
* [ ] Rework Configuration Method
* [X] New way to select an area _*Experimental*_
