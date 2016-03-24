(ns blogcljsfigwheel.core
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(def state (atom {:echo ""}))

(defn row [label & body]
  [:div.row
   [:div.col-md-2 [:span label]]
   [:div.col-md-3 body]])

(defn text-input [label state]
  [row label [:input {:type "text" :class "form-control" :on-change #(swap! state assoc :name (-> % .-target .-value))}]])

(defn post-echo [echo]
  (go
    (let [echo-response (<! (http/post (str "echo/" echo)))]
      (swap! state assoc :echo (:body echo-response)))))

(defn home-page []
  (let [local-state (atom {:name ""})]
    (fn []
      [:div.container
       [:div [:h2 "Welcome to my test cljsbuild"]
        [:div [:p "This is a test app that echos a name."]]
        [text-input "Enter name:" local-state]
        [:div.row
         [:div.col-md-3
          [:button {:type "submit"
                    :class "btn btn-default"
                    :on-click #(post-echo (:name @local-state))}
           "Submit"]]]
        [:div.row {:style {:margin-top "20px"}}
         [:div.col-md-12
          [:p (str "Echo from server: " (:echo @state))]]]]])))

(defn mount-root []
  (reagent/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))

(init!)
