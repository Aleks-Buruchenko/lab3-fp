package compact.fp.logic

import compact.fp.model._

object Compatibility {

  // Какие типы совместимы друг с другом
  val requiredPairs: Set[(ComponentType, ComponentType)] = Set(
    (CPU, Motherboard),
    (RAM, Motherboard),
    (GPU, Motherboard),
    (Storage, Motherboard)
  )

  // Проверяем, относится ли пара типов к обязательным
  private def isRequiredPair(a: Component, b: Component): Boolean =
    requiredPairs.contains((a.compType, b.compType)) ||
      requiredPairs.contains((b.compType, a.compType))

  // Совместимость:
  // - Если пара типов НЕ обязательная — они автоматически совместимы
  // - Если обязательная — проверяем по compatibleIds
  def isCompatible(a: Component, b: Component): Boolean =
    if (!isRequiredPair(a, b)) true
    else {
      val mb = Seq(a, b).find(_.compType == Motherboard).get
      mb.compatibleIds.contains(if (mb eq a) b.id else a.id)
    }

  def allCompatible(components: List[Component]): Boolean = {
    components.combinations(2).foreach {
      case List(a, b) =>
        val req = isRequiredPair(a, b)
        val compat = isCompatible(a, b)
        println(s"CHECK: ${a.id} <-> ${b.id} | required=$req | compatible=$compat")
    }

    components.combinations(2).forall {
      case List(a, b) => isCompatible(a, b)
    }
  }
}

