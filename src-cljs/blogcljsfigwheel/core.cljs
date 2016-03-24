(ns blogcljsfigwheel.core
  (:require [reagent.core :as reagent :refer [atom]]))

(defn row [label & body]
  [:div.row
   [:div.col-md-2 [:span label]]
   [:div.col-md-3 body]])

(defn text-input [label]
  [row label [:input {:type "text" :class "form-control"}]])

(defn home-page []
  [:div [:h2 "Welcome to my test cljsbuild"]
   [:div [:p "This is a test app that echos a name."]]
   [text-input "Enter name"]])

(defn mount-root []
  (reagent/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))

(init!)
