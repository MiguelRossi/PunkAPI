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
All the threads are handled using coroutines.

Libraries Used
--------------

From [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/):
- [Data Binding Library](https://developer.android.com/topic/libraries/data-binding)
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Navigation](https://developer.android.com/guide/navigation/)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)

From [Square](https://square.github.io/):
- [OkHttp](https://square.github.io/okhttp/)
- [Retrofit](https://square.github.io/retrofit/)
- [Moshi](https://github.com/square/moshi)

Others:
- [Glide](https://bumptech.github.io/glide/)

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

Branches whishlist
------------------

Several branches with:
- Pager
- MVP, MVC, MVI. Maybe VIBER too?
- Creating modules
- Find alternatives to the repository pattern (Mediator?)

Two more branches adding:
- Room
- Koin

A single branch with:
- Volley instead of Retrofit
- GSON instead of Moshi
- RxJava instead of coroutines
- Picasso instead of Glide

From the previous one, another two adding:
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
