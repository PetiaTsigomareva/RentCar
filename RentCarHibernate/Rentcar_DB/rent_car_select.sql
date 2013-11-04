select car.id,
       car.producer,
       car.modification,
       car.registration_number,
       rent.rent_date,
       renter.*
  from renter renter,
       rent rent,
       car car
 where renter.id = rent.renter_id
   and             rent.car_id = car.id
order by rent.rent_date
/
desc rent
/

