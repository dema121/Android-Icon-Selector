Android Icon Selector
=====================

Little program that helps Android Developers to quickly select/export only useful png from official material design icons.

<h3>Prerequisite</h3>
To use it you have to download the jar of this java program and download <a href="http://material-design.storage.googleapis.com/publish/v_2/material_ext_publish/0B08MbvYZK1iNUzJ4c1VXWDYzbTA/material-design-icons-1.0.1.zip">this</a> or <a href="https://github.com/google/material-design-icons">this from github</a> (official material design icons) 

<h3>Instruction</h3>
Then you can create your file to select the icons that you need.
In the file must be a row for each icons that contains the type of the icon (action, alert, editor, etc) and the icon name (ic\_error\_red\_48dp.png, ic\_mode\_edit\_black\_36dp.png) separated by a space.

Example of a row "file ic\_cloud\_upload\_white\_48dp.png"
Then you have 2 choices. If you want that the name remains the same you have all done.
Whereas if you want to rename EACH icon you can leave a blank row below and insert for each icon previously written one row with the corrispondent final name.

<h3>Execution</h3>
Once you have built your file you can run the program in a shell with:
```
java -jar AndroidIconSelector.jar directoryWhereIconsAre directoryWherePlaceFinalIcons inputFile.txt
```

Then you're asked to insert which DPIs you want to export.

_Please report bug or enhancement_
