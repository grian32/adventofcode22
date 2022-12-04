fun IntRange.hasAll(other: IntRange): Boolean {
    for (i in this) {
        if (!other.contains(i)) return false
    }

    return true
}

fun IntRange.hasAny(other: IntRange): Boolean {
    for (i in this) {
        if (other.contains(i)) return true
    }

    return false
}