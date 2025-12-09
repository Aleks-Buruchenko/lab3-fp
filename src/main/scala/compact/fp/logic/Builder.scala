package compact.fp.logic

import compact.fp.model._

object Builder {

  def buildConfiguration(components: List[Component]): Either[String, Configuration] =
    if (Compatibility.allCompatible(components))
      Right(Configuration(components))
    else
      Left("Найдены несовместимые компоненты.")
}
