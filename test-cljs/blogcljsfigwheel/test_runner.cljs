(ns blogcljsfigwheel.test-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [blogcljsfigwheel.core-test]))

(enable-console-print!)

(doo-tests 'blogcljsfigwheel.core-test)
