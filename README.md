# 🆕 PixLib is now renamed to Experience!

<img src="https://imgur.com/RTuYY8g.png" />

----

# Compatibility

Experience Library is developing under [spigot-api](https://hub.spigotmc.org/javadocs/bukkit/) version [1.21.1](https://helpch.at/docs/1.21.1/) so you should be fine on developing a bukkit, spigot, and paper plugin!

* [X] 1.21.1

Feel free to use this [Template](https://github.com/PinozenTH/PluginTemplate) for your plugin.

_**Note: I don't have plan to down grade this library to any spigot-api version under 1.21.1 for now.**_

> ⚠️ Caution this library is has develop just for pinont's plugin, Becareful of using this library on your own,<br>You may Experiences some bugs or problems<br>Due to my lack of Development Skills and Testing.

# Quick Start

Please follow the steps below to if you plan to use Experience flamework on your own plugin.

## Step 1: Import experiences into Maven/Gradle

If you use maven don't worry about repository, This library has located a repository to maven central.

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

## Step 2: Shading (Important)

**Experiences** comes with some plugins available for you such as MythicMobs, etc. so that you can access them when you are coding but don't need to include them as dependencies on your own.

Maven has a limitation whereby these plugins will end up in your plugin .jar file if you don't configure the maven-shade-plugin's includes section properly.

If you are a beginner all that's needed is copy paste the following section and drop it into your `<plugins>` section of pom.xml (if you already have such section there, remove it).

**Make sure to change `your.plugin.main.package` below to your own package name.**

If you want to compile a dependency to your jar, install it normally through the `<dependency>` directive, set it's scope to "compile" and then include it again. You can just duplicate the `<include>` and change it for your dependency.

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>

    <!-- Change version to the latest one from
         https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-shade-plugin -->
    <version>3.5.1</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <createDependencyReducedPom>false</createDependencyReducedPom>
        <artifactSet>
            <includes>
                <!-- Important: This will ensure only Experiences is shaded to your jar. If you have
                     other dependencies that should be compiled, duplicate this line for each. 
                     
                     ONLY ADD THE LIBRARIES HERE YOU WANT TO BE INCLUDED IN YOUR PLUGIN.JAR
                     -->
                <include>com.pinont.experiences</include>
            </includes>
        </artifactSet>
        <relocations>
            <!-- This moves Experiences into your own package in "lib" subpackage to prevent interference. -->
            <relocation>
                <pattern>com.pinont.experiences</pattern>
                <shadedPattern>your.plugin.main.package.lib</shadedPattern>
            </relocation>
        </relocations>
    </configuration>
</plugin>
```

## Step 3: extends ExpPlugin on your MainClass

**Experiences** is already extends JavaPlugin and has a method to handle `onEnable()` and `onDisable()` so you don't have to worry about register events, and commands excuter for this case.

Simply change from `JavaPlugin` to `ExpPlugin` and remove `onEnable()` and `onDisable()` then implements a code below.

If you are beginners in this case extends in java can be use for extends only on class.

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

> 🍵 Note: I'm not a profressional code developer, so feel free to **[PR](https://github.com/PinozenTH/Experiences-Lib/pulls)** an enchantment to my code if you think it's better to be.

# Features (WIP: I guess this will be finished on late 2025)

* [ ] Automatic command registration.
* [ ] Automatic event listener registration.
* [ ] Automatic configuration file generation.
* [ ] Automatic language file generation.
* [ ] Automatic database connection.
* [ ] Automatic plugin update checker.
* [ ] Automatic plugin metrics.
* [X] Simple method to create an Item using `ItemBuilder`.
* [X] Simple method to create an Entity using `EntityBuilder`. *(Without summoning the entity)* _***(Experimental)***_
* [X] Create a GUI using `Gui`.
* [ ] Rework Economy System.
* [X] Rework Scoreboard System. _***(Experimental)***_
* [ ] Rework Messaging Method.
* [ ] Rework Configuration Method.
* [X] New way to select an area. _***(Experimental)***_
