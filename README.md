
<img src="https://res.cloudinary.com/jimtheenchanter/image/upload/v1576664371/HillfortHere/hillforthere2-splash-readme.jpg" />



# hillforthere_2
Archaeological fieldwork Android App for students to navigate to and update images and coordinates of known hillforts in Ireland.

# Foundation
AndroidX - JetPack Libraries

# UI
Using Android's constraint layout the UI is designed to take up no more than 1 screen at a time i.e no scroll bar.  
All components have a hosizontal and vertical constraint defined.

# Behaviour
Menu - Context sensitive menu options including

- up
- add
- logout
- show all markers on map




# Architecture
Clean Architecure implemented with Model View Presenter (MVP) architectural pattern. This improves   
seperation of concerns in presentation logic and makes the code easy to read and maintain and provides   
a high degree of decoupling.
Room implemented for testing purposes and also utilises Google's Firebase for Authentication, Database  
and file Storage.

Register & Authenticate with Google Firebase.

Store, retrieve, create, edit and delete Hillforts with Firebase Database

Set locations of Hillfort from current phone location.

Map of all Hillforts

View/ Presenter Model  implemented.

# Sources
- Git Branching:  
https://nvie.com/posts/a-successful-git-branching-model/
https://stackabuse.com/git-merge-branch-into-master/
- Git Workflow: 
https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow
- Android JetPack Components:    
https://developer.android.com/jetpack/
- Buttons:    
https://developer.android.com/reference/android/widget/Button.html
- Statelist:  
https://developer.android.com/guide/topics/resources/drawable-resource.html#StateList
- Markdown Guide:  
https://www.markdownguide.org/cheat-sheet/
- Bottom Navigation  
https://medium.com/@suragch/how-to-add-a-bottom-navigation-bar-in-android-958ed728ef6c
https://code.tutsplus.com/tutorials/how-to-code-a-bottom-navigation-bar-for-an-android-app--cms-30305
- Separation of Concerns:  
https://en.wikipedia.org/wiki/Separation_of_concerns
- Checkbox  
https://developer.android.com/guide/topics/ui/controls/checkbox
/* Color Theme Swatches in Hex */
.Campfire-1-hex { color: #588C7E; }
.Campfire-2-hex { color: #F2E394; }
.Campfire-3-hex { color: #F2AE72; }
.Campfire-4-hex { color: #D96459; }
.Campfire-5-hex { color: #8C4646; }

/* Color Theme Swatches in RGBA */
.Campfire-1-rgba { color: rgba(87, 140, 126, 1); }
.Campfire-2-rgba { color: rgba(242, 226, 147, 1); }
.Campfire-3-rgba { color: rgba(242, 174, 114, 1); }
.Campfire-4-rgba { color: rgba(216, 100, 89, 1); }
.Campfire-5-rgba { color: rgba(140, 70, 70, 1); }

/* Color Theme Swatches in HSLA */
.Campfire-1-hsla { color: hsla(163, 22, 44, 1); }
.Campfire-2-hsla { color: hsla(50, 78, 76, 1); }
.Campfire-3-hsla { color: hsla(28, 83, 69, 1); }
.Campfire-4-hsla { color: hsla(5, 62, 60, 1); }
.Campfire-5-hsla { color: hsla(0, 33, 41, 1); }


<palette>
<color name='Campfire-1' rgb='588C7E' r='87' g='140' b='126' />
<color name='Campfire-2' rgb='F2E394' r='242' g='226' b='147' />
<color name='Campfire-3' rgb='F2AE72' r='242' g='174' b='114' />
<color name='Campfire-4' rgb='D96459' r='216' g='100' b='89' />
<color name='Campfire-5' rgb='8C4646' r='140' g='70' b='70' />
</palette>