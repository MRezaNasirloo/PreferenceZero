# Preference Zero

[ ![Download](https://api.bintray.com/packages/mrezanasirloo/maven/preference-zero/images/download.svg) ](https://bintray.com/mrezanasirloo/maven/preference-zero/_latestVersion)

A replacement for the built-in Android Preference UI API, completely based on custom views not Preference.

**NOTE:** This project was developed to backport Material Design style for pre-Lollipop
and being easily customizable and the ability to use the visual layout editor,
However Android Studio and Support Library includes these features now, but still this project has 
some useful features. 

![Demo gif](https://media.giphy.com/media/559q8q0bendPqKox8n/giphy.gif)

### Features:

1. Convenient to customize and very flexible.
2. Can be used in any layout file.
3. Supports nested dependencies on other preferences.

### Download
```groovy
implementation 'com.mrezanasirloo:preference-zero:0.9.1'
```

#### Limitation:

Unlike the built-in Preference API this project
doesn't support `PreferenceManager.setDefaultValues(context, R.xml.some_pref, false)`

##### Credits

Many thanks to [@mehdok](https://github.com/mehdok) for his contribution to this project.

#### License
```
MIT License

Copyright (c) 2018 M. Reza Nasirloo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```