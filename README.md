# Preference Zero

A replacement for the built-in Android Preference UI API, completely based on custom views not Preference.

NOTE: This project was developed to backport Material Design style for pre-Lollipop
and being easily customizable and the ability to use the visual layout editor,
However Android Studio and Support Library includes these features now.

![Demo gif](https://media.giphy.com/media/559q8q0bendPqKox8n/giphy.gif*D4Zxe8gKFTtoF4LcZ8CoNw.gif)

### Features:

1. Convenient to customize and very flexible.
2. Can be used in any layout file.
3. Supports nested dependencies on other preferences.

#### Limitation:

Unlike the built-in Preference API this project
doesn't support `PreferenceManager.setDefaultValues(context, R.xml.some_pref, false)`