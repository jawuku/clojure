
(defn leap-year? [year]
"Return `true` if `year` is a leap year in the gregorian calendar.
on every year that is evenly divisible by 4
except every year that is evenly divisible by 100
unless the year is also evenly divisible by 400"
  (or (zero? (mod year 400))
  
      (and (zero? (mod year 4))
        (not (zero? (mod year 100))))))
