package com.bawp.jettip_test.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EmptyHeight() = Spacer(Modifier.height(16.dp))

@Composable
fun EmptyHeight2() = Spacer(Modifier.height(32.dp))

@Composable
fun EmptyWidth() = Spacer(Modifier.width(16.dp))

@Composable
fun EmptyWidth2() = Spacer(Modifier.width(32.dp))