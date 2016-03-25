(ns blogcljsfigwheel.core
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [clojure.string :as str])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(def state (atom {:echo ""}))

(defn row [label & body]
  [:div.row
   [:div.col-md-2 [:span label]]
   [:div.col-md-3 body]])

(defn text-input [label state]
  [row label [:input {:type "text" :class "form-control" :on-change #(swap! state assoc :name (-> % .-target .-value))}]])

(defn echo [echo state]
  (go
    (let [echo-response (<! (http/post (str "echo/" echo)))]
      (swap! state assoc :echo (:body echo-response)))))

(def vowels [\a \e \i \o \u])

(defn encode [s]
  (when (not (or (= s "")
                 (nil? s)))
    (let [fst (first s) rst (rest s)]
      (if (some #{(str/lower-case fst)} vowels)
        (str s "way")
        (str (apply str rst) fst "ay")))))

(defn extract-first-word [sentence]
  (let [[fst-word & more] (str/split sentence #"\s")]
    [(if fst-word fst-word "")  (str/join " " more)]))

(defn pig-latin-first-word
  [sentence]
  (let [[fst more] (extract-first-word sentence)]
    (str/join " " [(encode fst) more])))

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
                    :on-click #(echo (:name @local-state) state)}
           "Submit"]]]
        (let [echo-from-server (:echo @state)]
          [:div
           [:div.row {:style {:margin-top "20px"}}
            [:div.col-md-12
             [:p (str "Echo from server: " echo-from-server)]]]
           [:div.row
            [:div.col-md-12
             [:p (str "Pig latin echo: " (pig-latin-first-word echo-from-server))]]]])]])))

(defn mount-root []
  (reagent/render [home-page] (.getElementById js/document "app")))

(defn ^:export main
  []
  (enable-console-print!)
  (mount-root))
