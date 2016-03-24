(ns blogcljsfigwheel.core
  (:require [com.stuartsierra.component :as component]
            [blogcljsfigwheel.http :as http])
  (:gen-class))

(def config {:server {:port 3000}})

(defn my-system [config]
  (let [{:keys [server]} config]
    (component/system-map
     :app (http/new-server-component (:server config)))))

(defn -main []
  (component/start (my-system config)))
