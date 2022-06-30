package com.example.issue_tracker.common

import kotlinx.coroutines.flow.MutableStateFlow

fun <E> MutableStateFlow<MutableList<E>>.addElement(element: E) {
    if (element == null) {
        return
    }
    val tempMutableList = mutableListOf<E>()
    tempMutableList.addAll(this.value)
    tempMutableList.add(element)
    this.value = tempMutableList
}

fun <E> MutableStateFlow<MutableList<E>>.removeElement(element: E) {
    if (element == null) {
        return
    }
    val tempMutableList = mutableListOf<E>()
    tempMutableList.addAll(this.value)
    tempMutableList.remove(element)
    this.value = tempMutableList
}

fun <E> MutableStateFlow<MutableList<E>>.removeAllElement() {
    this.value = mutableListOf()
}