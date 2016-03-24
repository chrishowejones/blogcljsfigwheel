(ns blogcljsfigwheel.http
  (:require [com.stuartsierra.component :as component]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [include-js]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.adapter.jetty :refer [run-jetty]]))

(def home-page
  (html
   [:html
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1"}]
     [:link {:rel "stylesheet" :href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" :integrity "sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" :crossorigin "anonymous"}]]
    [:body
     [:div#app
      [:h3 "Loading...."]
      [:p "Loading application. Please wait...."]]
     (include-js "js/compiled/blogcljsfigwheel.js")]]))

(defroutes app-routes
  (GET "/" [] home-page)
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defrecord Server-component [server-options]
  component/Lifecycle
  (start [this]
    (let [server (run-jetty app server-options)]
      (assoc this :web-server server)))
  (stop [this]
    (let [server (:web-server this)]
      (.stop server))
    nil))

(defn new-server-component [server-options]
  (->Server-component server-options))

(comment
  (def server-component (new-server-component {:port 3000 :join? false}))
  (.start server-component)
  (.stop server-component)

  )
