package compact.fp.logic

import compact.fp.model._

object Builder {

  def buildConfiguration(components: List[Component]): Either[String, Configuration] = {
    if (components.isEmpty) {
      Left("Не введено ни одного компонента.")
    }
    else if (!components.exists(_.compType == Motherboard)) {
      Left("В конфигурации отсутствует материнская плата — сборка невозможна.")
    }
    else if (!Compatibility.allCompatible(components)) {
      Left("Найдены несовместимые компоненты.")
    }
    else {
      Right(Configuration(components))
    }
  }
}
