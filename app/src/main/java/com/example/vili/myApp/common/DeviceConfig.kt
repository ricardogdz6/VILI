package viliApp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import viliApp.DeviceConfig.Companion.setDpValues

@Composable
fun getDeviceConfig() {

    val configuration = LocalConfiguration.current


    setDpValues(configuration.screenHeightDp.dp,configuration.screenWidthDp.dp, LocalDensity.current.density )
}

class DeviceConfig(){

    companion object{

        var screenHeight : Dp = 0.dp
        var screenWidth : Dp = 0.dp
        var dpi : Float = 0f

        fun setDpValues(height: Dp, width: Dp, dpi: Float){
            screenHeight = height
            screenWidth = width
            this.dpi = dpi
        }

        fun returnHeight():Dp{
            return screenHeight
        }

        fun returnWidth():Dp{
            return screenWidth
        }

        fun heightPercentage(targetPercentage : Int): Dp {
            return ((targetPercentage * screenHeight.toString().substringBefore('.').toInt())/100).dp
        }

        fun widthPercentage(targetPercentage : Int): Dp {
            return ((targetPercentage * screenWidth.toString().substringBefore('.').toInt())/100).dp
        }

        fun DPheightPercentage(initialDP : Dp, targetPercentage : Int):Dp{
            return ((targetPercentage * initialDP.toString().substringBefore('.').toInt())/100).dp
        }

        fun DPwidthPercentage(initialDP : Dp, targetPercentage : Int):Dp{
            return ((targetPercentage * initialDP.toString().substringBefore('.').toInt())/100).dp
        }


    }

}