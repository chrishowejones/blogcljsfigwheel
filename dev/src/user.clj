(ns user
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.namespace.repl :refer [refresh]]
            [blogcljsfigwheel.core :as app]
            [figwheel-sidecar.repl-api :as ra]))

(def system nil)

(def server-port-num 8000)

(defn init []
  (alter-var-root #'system
                  (constantly (app/my-system {:server {:port server-port-num :join? false}}))))

(defn start []
  (alter-var-root #'system component/start))

(defn stop []
  (alter-var-root #'system
                  (fn [s] (when s (component/stop s)))))

(defn go []
  (init)
  (start))

(defn reset []
  (stop)
  (refresh :after 'user/go))

;; As figwheel doesn't seem to properly merge cljsbuild config from profiles
;; we have to define our figwheel config here and use some helper fns to start
;; and stop figwheel.

(def figwheel-config
  {:figwheel-options {}
   :build-ids ["dev"]
   :all-builds
   [{:id "dev"
     :source-paths ["src-cljs"]
     :figwheel true
     :compiler {:main "blogcljsfigwheel.core"
                :output-to "target/cljsbuild/public/js/compiled/blogcljsfigwheel.js"
                :output-dir "target/cljsbuild/public/js/compiled/out"
                :asset-path "js/compiled/out"}}]})


(defn start-figwheel! [] (do (ra/start-figwheel! figwheel-config) nil))

(defn stop-figwheel! [] (do (ra/stop-figwheel!) nil))

(defn cljs-repl [] (ra/cljs-repl))
