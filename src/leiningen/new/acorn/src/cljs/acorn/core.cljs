(ns {{project-ns}}.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defonce timer (atom (js/Date.)))
(defonce time-color (atom "#f34"))

(defn update-time [time]
  ;; Update the time every 1/10 second to be accurate...
  (js/setTimeout #(reset! time (js/Date.)) 100))

(defn clock []
  (update-time timer)
  (let [time-str (-> @timer .toTimeString (clojure.string/split " ") first)]
    [:h1
     {:style {:color @time-color}}
     time-str]))

(defn main []
  (reagent/render-component (fn [] [clock])
                            (. js/document (getElementById "app"))))
