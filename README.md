# MinecraftSessionPatcher
[THIS TOOL IS FOR PURELY EDUCATIONAL PURPOSES! I, SEAN DISHMAN, AM NOT RESPONSIBLE FOR ANY LEGAL OR ADMINISTRATIVE ACTION TAKEN AGAINST YOU IF YOU USE THIS FOR MALICIOUS INTENT!]

Using a username, session ID, and UUID, sign into other Minecraft accounts temporarily.

```
mcsessionpatcher - Steal accounts via session token & UUID
USAGE: 
ARGS: 
nothing - Launch this application in GUI user-friendly mode, 
username session uuid (in that order) - replace using command-line. 
END
```

If you launch it without command-line arguments, it will bring up a nice universal GUI.

Why is this being released?
Too many people are elitist and are not sharing this--I want that to change. Additionally, I want Hypixel and Mojang to do something about this, instead of telling everyone to eat cake and be happy with it; until then, may chaos reign!


TO DO THIS MANUALLY,

You must download Optifine 1.12.2 G5 (http://adfoc.us/serve/sitelinks/?id=475250&url=http://optifine.net/adloadx?f=OptiFine_1.12.2_HD_U_G5.jar&x=f4d8) and then you must replace the following in ~/minecraft/versions/1.12.2-OptiFine_HD_U_G5/1.12.2-OptiFine_HD_U_G5.json (or wherever your Minecraft folder is).

```
\"minecraftArguments\": \"--username [ENTER THE USERNAME HERE] --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid [ENTER THE UUID HERE] --accessToken [ENTER THE SESSION ID/TOKEN HERE] --userType ${user_type} --versionType ${version_type}  --tweakClass optifine.OptiFineTweaker\""
```

Then, you launch that Optifine version and it SHOULD work.

