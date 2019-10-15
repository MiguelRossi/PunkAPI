PunkAPI (alpha)
===============

An app which takes the [Punk API](https://punkapi.com/ "Brewdog's DIY Dog searchable, filterable API that's completely free and open source") and does searches through [Brewdog's DIY Dog](https://www.brewdog.com/uk/beers/diy-dog "BrewDog's beer recipe compilation")'s back catalogue of beer.

Introduction
------------

This repository is a continuous experiment on different architectures patterns and libraries.

Each branch is a sample of a different implementation.<br />
Each implementation differs from others by a library or an architectural pattern.

Description
-----------

Following [Google recommendations](https://developer.android.com/jetpack/docs/guide "Guide to app architecture") this branch implements the Repository Pattern with MVVM.<br />
All the requests are done directly to the network and there is no database.<br />
Threads are managed using RxJava2, Volley as web client, JSON parsing using GSON and Picasso for handling images.

Libraries Used
--------------

From [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/):
- [Data Binding Library](https://developer.android.com/topic/libraries/data-binding)
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Navigation](https://developer.android.com/guide/navigation/)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)

From Google:
- [Volley](https://developer.android.com/training/volley)
- [GSON](https://github.com/google/gson)

From [Square](https://square.github.io/):
- [Picasso](https://square.github.io/picasso/)

From [ReactiveX](http://reactivex.io/):
- [RxAndroid](https://github.com/ReactiveX/RxAndroid)

And helping in the development:
- [LeakCanary](https://square.github.io/leakcanary/)
- [Timber](https://github.com/JakeWharton/timber)

For testing:
- [JUnit](https://developer.android.com/training/testing/unit-testing/local-unit-tests)
- [Truth](https://truth.dev/)
- [Mockito](https://site.mockito.org/)
- [Espresso](https://developer.android.com/training/testing/espresso)
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)

Current Branches
----------------

- [coroutines_retrofit_moshi_glide_mvvm_repositoryPattern](https://github.com/MiguelRossi/PunkAPI/tree/coroutines_retrofit_moshi_glide_mvvm_repositoryPattern)
- [coroutines_retrofit_moshi_glide_mvvm_repositoryPattern_room](https://github.com/MiguelRossi/PunkAPI/tree/coroutines_retrofit_moshi_glide_mvvm_repositoryPattern_room)
- [rxjava_volley_gson_picasso_mvvm_repositoryPattern](https://github.com/MiguelRossi/PunkAPI/tree/rxjava_volley_gson_picasso_mvvm_repositoryPattern)

Branches whishlist
------------------

Several branches with:
- Pager
- MVP, MVC, MVI. Maybe VIBER too?
- Creating modules
- Find alternatives to the repository pattern (Mediator?)

One more branches adding:
- Koin

Another two adding:
- Realm
- Dagger2

...not necessarily in that order

License
-------

The MIT License (MIT)

Copyright 2019 Miguel Rossi

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
