# blogcljsfigwheel

Source code for blog on how to use figwheel with new nRepl style in a
cljs project and separate out your figwheel/test config from your
production config.

## Installation

Download from http://github.com/chrishowejones/blogcljsfigwheel

## Usage

Build using:

```
$ lein uberjar
```
Run using:

    $ java -jar blogcljsfigwheel-0.1.0-standalone.jar

Then open a browser at `http://localhost:3000`

### Run server from REPL

```
$ lein repl
...
user=> (go)
2016-03-26 15:31:22.826:INFO:oejs.Server:nREPL-worker-0: jetty-9.2.10.v20150310
2016-03-26 15:31:22.862:INFO:oejs.ServerConnector:nREPL-worker-0: Started ServerConnector@ca116e6{HTTP/1.1}{0.0.0.0:8000}
2016-03-26 15:31:22.863:INFO:oejs.Server:nREPL-worker-0: Started @15729ms
#<SystemMap>

```

The server starts on port 8000 when started from the `(go)` function.

Run `(reset)` to reload the server if you edit the source or `(stop)`
to shut the server.

### Run figwheel to auto reload client in REPL

To run figwheel in a REPL:

```
$ lein repl
...
user=> (start-figwheel!)
Figwheel: Starting server at http://localhost:3449
Port 3449 is already being used. Are you running another Figwheel instance? If you want to run two Figwheel instances add a new :server-port (i.e. :server-port 3450) to Figwheel's config options in your project.clj
Figwheel: Watching build - dev
Compiling "target/cljsbuild/public/js/compiled/blogcljsfigwheel.js" from ["src-cljs" "test-cljs"]...
Successfully compiled "target/cljsbuild/public/js/compiled/blogcljsfigwheel.js" in 6.527 seconds.
nil
user=> (cljs-repl)
Launching ClojureScript REPL for build: dev
Figwheel Controls:
          (stop-autobuild)                ;; stops Figwheel autobuilder
          (start-autobuild [id ...])      ;; starts autobuilder focused on optional ids
          (switch-to-build id ...)        ;; switches autobuilder to different build
          (reset-autobuild)               ;; stops, cleans, and starts autobuilder
          (reload-config)                 ;; reloads build config and resets autobuild
          (build-once [id ...])           ;; builds source one time
          (clean-builds [id ..])          ;; deletes compiled cljs target files
          (print-config [id ...])         ;; prints out build configurations
          (fig-status)                    ;; displays current state of system
  Switch REPL build focus:
          :cljs/quit                      ;; allows you to switch REPL to another build
    Docs: (doc function-name-here)
    Exit: Control+C or :cljs/quit
 Results: Stored in vars *1, *2, *3, *e holds last exception object
Prompt will show when Figwheel connects to your application

```

## License

Copyright © 2016 Chris Howe-Jones

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
