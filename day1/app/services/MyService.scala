package services

import com.google.inject.ImplementedBy

@ImplementedBy(classOf[MyServiceImpl])
trait MyService {
  def find(): String
}

class MyServiceImpl extends MyService {
  def find(): String = "my service"
}
