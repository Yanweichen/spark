package com.ywc

import java.time.LocalDateTime

import com.xy.Sgp4
import com.xy.sgp4v1.constant.{Coordinate, DistUnit}
import com.xy.sgp4v1.coordinate.CoordinateConverter
import com.xy.sgp4v1.unit.UnitConverter

object Test {
  def main(args: Array[String]): Unit = {
    val line1 = "1 39766U 14029A   17363.50629496  .00000227  00000-0  37330-4 0  9999"
    val line2 = "2 39766  97.9214  98.0371 0001567  8" +
      ".3995 273.7391 14.79474030194489"
    Sgp4.setResourcePath("C:\\Git_Project\\test\\libs\\sgp4Data")

    val date = LocalDateTime.now()
    val a = Sgp4.sgp4v1(line1, line2, Coordinate.ECEF, DistUnit.KM, 1.0, date, date.plusDays(1))
    //    val a = Sgp4.sgp4v1(line1, line2, CoordinateConverter.ecefConvert, UnitConverter.kmConvert, 1.0, null, null)
    println(a.get(0).getX)
  }
}
