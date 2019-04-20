![](media/6d3430e14da579aee89febc1e1fd37f0.jpg)

Touch Input Based User Authentication in Smartphones

*Abstract*—The usage of touchscreen-based smartphones is increasing every year
taking up majority of the smartphone market. With the number of user increasing
rapidly more and more store their important credentials and personal data on
their smartphones, making them more vulnerable to attackers. This project
proposes touch-based user authentication scheme that is based on touch dynamics
that uses a set of behavioural features related to the touch dynamics for
accurate user authentication.

This project will require usage data from about 20 android users. This touch
data will be classified by several known machine learning classifiers.

1.  **Literature Review**

Touch input identification uses behavioural biometrics to differentiate between
the actual user and an unauthorized user. Behavioural biometric methods use
actions from a user such as key stroke dynamics or mouse dynamics.

Touch dynamics are proposed in S. Kolly’s [1] article for user identification.
These include touch duration, direction of touch, pressure applied on the
screen. A set of 9 features is used as a dataset for classification. The author
uses several known classifiers to compute the error and how well each classifier
works. The author used weka machine learning toolkit for classifiers.

Since touch dynamics are similar to mouse dynamics existing methods for mouse
events can be used. Drag and button click events can be mapped to screen drag
and tap events for a touch screen but addition features are required to properly
identify an individual. The data was collected by a quiz application recording
data of 14,890 users. The authors used smartphones with similar pixel densities
and display sizes. But another problem discussed in the article is that the
pressure sensor gives different values for different phones. And yet they still
use different smartphones.

The identifier works with 80% accuracy for smaller data sets (5 people) and
using more samples does not significantly increase the accuracy.

However, this scheme fails for multi finger touches and makes feature extraction
more complex.

Meng’s [3] article proposes an authentication method using touch dynamics. The
author adds features such as average multi touches, average touch speed and
angle.

The data was collected from 20 users all given similar android smartphones with
stock version of android to makes changes to the application framework layer of
Android OS.

![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig1.png)

The author suggests that a user performs relatively more touch inputs in a
particular direction. This is illustrated in Fig [1]

Fig [1] User1 has a different direction of movement from User2 [3].

Using touchscreen input for continuous authentication [5] requires the touch
input to be recorded every time a user performs a trigger action. While other
approaches require the user to be in a certain application for the recognition
to work or performing a simple tap gesture provides very few features for
authentication. For continuous authentication, trigger actions are defined such
as vertical/horizontal swipe, pinch to zoom. The authentication could run in the
background and register data to its log when a trigger action is performed.

The first phase like any other machine learning application will be an enrolment
phase, during which the user will have to use traditional authentication
schemes, such as PIN or password. As the user uses the phone with enough
triggers actions such that the distribution of the touch input feature converges
to an equilibrium, we assume that the model is trained to identify the user.

One problem with this approach is that the Android OS does not disclose touch
input information other than the application asking for the raw input data. The
author has made an application [5] with image view and web view panels that can
record the individual touch data.

For the dataset, each individual swipe is recorded separately.

Some things to consider are that as soon as the user becomes familiar with an
application, their touch input pattern and speed changes. To keep these to the
minimum, the order of the task panels in which they appear will be randomized.
The research conducted in M. Frank’ s [5] article used 5 different phones
knowing the fact that pressure values will be different for each phone. Only
thing common between the phone is display size, pixel density and the
resolution. The article does not mention this problem.

1.  **Methodology**

For simplicity and to limit the project scope we limited our behavioural
biometric to touch dynamics not supporting multi touch gestures. Majority of the
previous implementations have made changes to the android OS.

For this project we made an android app that uses Android’s standard MotionEvent
methods for feature extraction [6].

1.  **FEATURE EXTRACTION**

The following features are be extracted from user touch input.

Table 1. Features extracted from touch input.

| Finger pressure   | By using android library getPressure() which returns pressure in kilopascals.                                                                               |
|-------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Finger Size       | Using getSize() method. Returns number of pixels.                                                                                                           |
| Time (ms)         | Extracted using the two methods getDownTime () and getEventTime () respectively. This can also be done using systemTime() to get difference between events. |
| Acceleration      | Can be calculated from speed and distance.                                                                                                                  |
| Distance          | √(𝑥2 −𝑥1)2 +(𝑦2 −𝑦1)2                                                                                                                                       |
| TouchMajor        | Using getTouchMajor()                                                                                                                                       |
| TouchMinor        | Using getTouchMinor()                                                                                                                                       |
| Touch Coordinates | GetX() and GetY() methods to get X, Y coordinates respectively.                                                                                             |

![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig2.png)

A sample of the data is shown in Fig [2]

1.  **NORMALIZATION**

As you can see from fig [2] the values from the getPressure () and getSize () as
well the speed is within the range of [0,1] thus these do not need to be
normalized.

However, the rest of the values can be normalized using a min-max function [7].

Since normalization reduces the quality of the sample data, we reduced the
number of features actually used to distinguish a user. This makes
classification easier and faster. Some features do not change significantly
among users these are a form of redundant data so they were removed.

This required testing the dataset against the gestures we used to record user
input for feature extraction.

1.  **CLASSIFICATION**

User classification is done through classifier functions. We are using Knn
Classifier for our project as it is easy to implement and have better accuracy
then naïve based used in past research and projects.

We have also tested are dataset on Random Forest Classifier and Gaussian Naïve
Bayes Classifier.

1.  **Accuracy**

    Since our classifier is not an ideal classifier, its accuracy is not 100%.
    The following are used to measure the accuracy of touch dynamics
    authentication:

-   CONFUSION MATRIX

-   CLASSIFICATION REPORT

1.  **KNN Classification Results**

![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig3.png)

**Fig[3]** Confusion matrix for KNN Classifier

![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig4.1.png)

![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig5.png)

**Fig[4]** Classification Report for KNN Classifier

**Fig[5]** Accuracy for increasing number of Neighbours

Using Grid Search Cross Validation, we have found the optimal value of *k* for
our dataset is *k*=19.

![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig6.png)

**Random Forest Classifier Results**

**Fig[6]** Confusion Matrix for RandomForest Classifier

![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig7.png)


**Fig[7]** Classification Report for RandomForest Classifier.

![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig8.png)

**Fig[8]** Accuracy for varying number of estimators

1.  **Naïve Bayes Classifier Results**

![](![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig9.png))

**Fig[9]** Confusion Matrix for Naïve Bayes Classifier

**Fig[9]** Classification Report for Naïve Bayes Classifier

**E. Tools**

Android Studio was used to build the android application. We attempted to export
our Classifier Model in PMML and the used it with JPMML for Android but the
library works on older versions of android (API 17) which are not being used
today. There is port of Weka called Weka for Android but it is not a full port
and has limited functionality. However, we implemented our own classifiers in
java and attached it with android studio into our application.

1.  **OUR WORK.**

2.  **DATASET**

We initially used S. Kolly’s [1] dataset in our project for user identification.
The dataset includes the following features.

-   Phone ID

-   User ID

-   Document ID

-   Time[ms]

-   Action

-   Phone Orientation

-   X-coordinate

-   Y-coordinate

-   Pressure applied

-   Area covered

-   Finger orientation

![](![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig10.png))

After HyperParameter Tuning we have shortlisted the following features that can
be used to classify behavioural analytics.

![](![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig11.png))

**Fig[10]** Features selected after hyperparameter tuning.

1.  **APPLICATION**

-   **INTERFACE**

The application has two activities, data collection and user authentication.

The data collection phase consists of sessions. Each session will contain a
number of tasks.

Task 1 contains a sequence of buttons and a slider. The user has to press the
buttons in a predefined sequence in order to move to the next task. This task
gives us the touch duration of tap gestures and slide actions.

Task 2 contains a WebView and the user has to scroll through a Wikipedia article
[2].

This task lets us calculate the average swipe distance, speed and pressure
applied onto the screen.

User Modes lets us specify the type of user data i.e. input data is from the
owner or some guest user.

Fig [11] User Modes

![](![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig12.png))

![](![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig13.png))

Fig [12] Data Collection Activity 1

![](![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig14.png))

Fig [12] Data Collection Activity 2

-   **DATA COLLECTION/ STORAGE**

The values for touch input are obtained using

Using methods from the motion event class on Android.

The touch data is directly stored to a CSV file within the device Our classifier
then uses CSV files to classify users.

![](![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig15.png))

Fig [13] Data Collected Through Mobile Application

1.  **FINAL PRODUCT**

The final product is an android that can recognize a user based on the stored
data set.

![](![](https://github.com/msannan2/Touch_Analytics/blob/master/Images/fig16.png))

There is a mode in the app that indicate which user is using phone by
classifying using Knn classifier function.

1.  **FUTURE GOALS**

>   If the accuracy is acceptable, our goal is to also implement a pattern
>   unlock model with the same functionality by adding some additional
>   attributes and using Random Forest as a classifier.

References

1.  S. M. Kolly, R. Wattenhofer, and S. Welten, “A personal touch,” *Proceedings
    of the Third International Workshop on Sensing Applications on Mobile Phones
    - PhoneSense 12*, 2012.

2.  “Keystroke Authentication with a Capacitive Display using Different Mobile
    Devices,” *Proceedings of the 10th International Conference on Security and
    Cryptography*, 2013.

3.  Y. Meng, D. S. Wong, R. Schlegel, and L.-F. Kwok, “Touch Gestures Based
    Biometric Authentication Scheme for Touchscreen Mobile Phones,” *Information
    Security and Cryptology Lecture Notes in Computer Science*, pp. 331–350,
    2013.

4.  Abdulhakim Alariki, Ala & Manaf, Azizah & Mousavi, Seyed Mojtaba. (2016).
    Features Extraction Scheme for Behavioural Biometric Authentication in
    Touchscreen Mobile Devices. International Journal of Applied Engineering
    Research. 11.

5.  M. Frank, R. Biedert, E. Ma, I. Martinovic, and D. Song, “Touchalytics: On
    the Applicability of Touchscreen Input as a Behavioral Biometric for
    Continuous Authentication,” *IEEE Transactions on Information Forensics and
    Security*, vol. 8, no. 1, pp. 136–148, 2013.

6.  “android.view.MotionEvent,” *Android Developers*, 07-Mar-2018. [Online].
    Available:
    https://developer.android.com/reference/android/view/MotionEvent.html.

7.  A. E. Hassanien, M. F. Tolba, and A. T. Azar, *Advanced machine learning
    technologies and applications: second international conference, AMLTA 2014,
    Cairo, Egypt, November 28-30, 2014, proceedings*. Cham: Springer, 2014.
    archive/macros/latex/contrib/supported/IEEEtran/

8.  “Weka 3: Data Mining Software in Java,” *Weka 3 - Data Mining with Open
    Source Machine Learning Software in Java*. [Online]. Available:
    https://www.cs.waikato.ac.nz/ml/weka/. [Accessed: 13-Mar-2018].

9.  “scikit-learn,” *scikit-learn: machine learning in Python - scikit-learn
    0.19.1 documentation*. [Online]. Available:
    http://scikit-learn.org/stable/index.html. [Accessed: 13-Mar-2018].

10. “nok/sklearn-porter,” *GitHub*, 18-Feb-2018. [Online]. Available:
    https://github.com/nok/sklearn-porter. [Accessed: 13-Mar-2018].
