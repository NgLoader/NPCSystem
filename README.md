<img align="right" src="https://user-images.githubusercontent.com/13753840/116860045-3dff0500-ac01-11eb-86c4-aea9161fdcd8.png" height="200" width="200">

# NPC API - NPCSystem
[![Release Status](https://github.com/NgLoader/NPCSystem/workflows/Releases/badge.svg)](https://github.com/NgLoader/NPCSystem/releases/latest) [![Build Status](https://github.com/NgLoader/NPCSystem/workflows/Build/badge.svg)](https://github.com/NgLoader/NPCSSystem/actions?query=workflow%3ABuild)

### Features
* Plug & Play
* Support for Spigot based servers 1.17+ 

## Requirements
- Java 16 or higher
- Spigot and (proably) any other fork of Spigot (1.17 or higher)
- [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997) latest

## Developer

### Clone
1. Clone this repo "git clone https://github.com/NgLoader/NPCSystem.git"
2. Open eclipse and right click on the "Project Explorer"
3. Click "Import..."
4. Maven -> Existing Maven Projects
5. Select the downloaded repo

### Build
1. Click right click on the Orebfuscator-repo folder and select "Run as" -> "Maven Build..."
2. Put into Goals this "clean compile package"
3. Click Run
4. Your jar will be builded under the folder "target"

### Maven
```maven
      <repositories>
		<repository>
			<id>ngloader-repo</id>
			<url>https://nexus.wuffy.eu/repository/maven-releases</url>
		</repository>
       </repositories>


       <dependency>
            <groupId>de.ngloader</groupId>
            <artifactId>npcsystem</artifactId>
            <version>LATEST</version>
       </dependency>
```

### Release a new version
1. git tag **version** -m "**description**"
2. git push origin **version**