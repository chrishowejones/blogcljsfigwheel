(defproject blogcljsfigwheel "0.1.0-SNAPSHOT"
  :description "Example build using cljs and figwheel"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.stuartsierra/component "0.3.1"]
                 [compojure "1.5.0"]
                 [ring/ring-defaults "0.2.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [ring-middleware-format "0.7.0"]
                 [hiccup "1.0.5"]
                 ;; clojurescript dependencies
                 [org.clojure/clojurescript "1.7.170" :exclusions [org.apache.ant/ant]]
                 [reagent "0.5.1" :exclusions [org.clojure/tools.reader]]
                 [cljs-http "0.1.38"]
                 [org.clojure/core.async "0.2.374"]]
  :source-paths ["src"]
  :main blogcljsfigwheel.core
  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.1"]]
  :resource-paths ["resources" "target/cljsbuild"]
  :target-path "target/%s"
  :cljsbuild {:builds [{:id "app"
                        :source-paths ["src-cljs"]
                        :compiler {:main "blogcljsfigwheel.core"
                                   :output-to "target/cljsbuild/public/js/compiled/blogcljsfigwheel.js"
                                   :output-dir "target/cljsbuild/public/js/compiled/out"
                                   :asset-path "js/compiled/out"}}]}
  :profiles {:uberjar {:aot :all
                       :prep-tasks ["compile" ["cljsbuild" "once" "app"]]}
             :dev {:source-paths ["dev/src"]
                   :dependencies [[figwheel-sidecar "0.5.1"]
                                  [org.clojure/tools.namespace "0.2.3"]
                                  [com.cemerick/piggieback "0.2.1"]]}}
  :repl-options {:init-ns user
                 :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]})
