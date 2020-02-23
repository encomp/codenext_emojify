# Emojify Me

### Exercise One: Add the Vision dependency

The goal is to look for the Mobile Vision dependency that needs to be
added to our `build.gradle` file.

##### [What is computer vision](https://www.youtube.com/watch?v=_99V1rUNFa4&feature=emb_logo)?
Computer Vision is the science and technology for building artificial
systems that obtain information from images or multi-dimensional data.
However, a significant part of AI deals with planning for system/machine
which can perform mechanical actions.

#### Add Mobile Vision Library dependency
Visit the Android Vision [GitHub](https://github.com/googlesamples/android-vision)
and look for the setup of the vision library.

1. Go to the following folder

```
 visionSamples/FaceTracker/app
```

2. Open the file `build.gradle` and look for the dependency called:

```
play-services-vision
```

3. Look for the latest version of the
[play-services-vision](https://mvnrepository.com/artifact/com.google.android.gms/play-services-vision)
library.

4. Finally add the dependency on your
[`build.gradle`](https://github.com/encomp/codenext_emojify/blob/02-branch/app/build.gradle#L39).

### Lastly do not forget to test on your phone.
After adding the library on your `build.gradle` file make sure you are
able to `Rebuild` your project on Andorid Studio. Finally, test your
project on your phone.