(ns co.gaiwan.paged-js
  (:require
   ["pagedjs" :refer [Previewer]]
   ["react-dom/client" :refer [createRoot]]
   ["react" :as react]
   [reagent.core :as reagent]))

(defonce root (createRoot
               (js/document.getElementById "app")))

(defonce state (reagent/atom {:count 25}))

(defn book []
  [:div#book
   [:table
    [:thead
     [:tr
      [:th "Head1"] [:th "Head2"]]]
    [:tbody
     (repeat
      100
      [:tr
       [:td "item"] [:td "100"]])]]
   ])

(defonce paged-js (Previewer.))

(defn app []
  ;; (react/useLayoutEffect
  ;;  (fn []
  ;;    (js/console.log "HERE")
  ;;    (.preview paged-js
  ;;              (.-innerHTML (js/document.querySelector "#book"))
  ;;              #js ["print_styles.css"]
  ;;              (js/document.querySelector "#preview"))
  ;;    (fn cleanup []
  ;;      (.. paged-js polisher destroy)
  ;;      (.. paged-js removeStyles)))
  ;;  #js [])
  [:div
   [book]
   [:input {:on-change #(swap! state assoc :count (js/parseInt (.. % -target -value)))
            :value (str (:count @state))}]])
#_
(rdom/render [app]
             (js/document.getElementById "app"))
(defn init
  []
  (.render root (reagent/as-element [:f> app])))

(defn ^:dev/after-load re-render
  []
  ;; The `:dev/after-load` metadata causes this function to be called
  ;; after shadow-cljs hot-reloads code.
  ;; This function is called implicitly by its annotation.
  (init))

(init)
