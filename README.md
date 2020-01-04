
<img src="https://res.cloudinary.com/jimtheenchanter/image/upload/v1576664371/HillfortHere/hillforthere2-splash-readme.jpg" />



# hillforthere_2
Archaeological fieldwork Android App for students to navigate to and update images and coordinates of known hillforts in Ireland.

# Foundation
AndroidX - JetPack Libraries.

# UI
Using Android's constraint layout the UI is designed to take up no more than 1 screen at a time i.e no scroll bar.  
Both horizontal and vertical constraint views defined and a landscape version for most screens.

# Behaviour
Menu - Context sensitive menu options including

- Up/back
- Add new hillfort
- Log out
- Show map displaying all hillforts



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

View/ Presenter Model implemented.

Local database Room implemented for development purposes

# Version Control

Git commit history available here 
https://github.com/jimtheenchanter/hillforthere_2/


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
- Search
https://developer.android.com/guide/topics/search/search-dialog
- Buttons
https://developer.android.com/guide/topics/ui/controls/button



