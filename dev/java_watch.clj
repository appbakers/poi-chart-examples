(ns java-watch
  "watch java source and compile"
  (:require [virgil :as vg]))


(comment


  (vg/watch "src/java")


  (import '[examples BarChart PieChart PieChartCompat])


  (BarChart/main (into-array String []))

  (PieChart/main (into-array String []))

  (PieChartCompat/main (into-array String []))



  ;;
  )
