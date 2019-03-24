#!/bin/bash

ROOTDIR="`pwd`"

cmd_to_run="cd '$ROOTDIR' && bash gradlew fortune-cookie-app-server:bootrun"
osascript -e "tell application \"Terminal\" to do script \"$cmd_to_run\""

cmd_to_run="cd '$ROOTDIR' && bash gradlew fortune-cookie-fulfillment-service:bootrun"
osascript -e "tell application \"Terminal\" to do script \"$cmd_to_run\""

cmd_to_run="cd '$ROOTDIR' && bash gradlew fortune-cookie-mailing-service:bootrun"
osascript -e "tell application \"Terminal\" to do script \"$cmd_to_run\""

cmd_to_run="cd '$ROOTDIR' && bash gradlew fortune-cookie-product-service:bootrun"
osascript -e "tell application \"Terminal\" to do script \"$cmd_to_run\""

cmd_to_run="cd '$ROOTDIR' && bash gradlew fortune-cookie-edge-server:bootrun"
osascript -e "tell application \"Terminal\" to do script \"$cmd_to_run\""

cmd_to_run="cd '$ROOTDIR/fortune-cookie-app' && npm run start"
osascript -e "tell application \"Terminal\" to do script \"$cmd_to_run\""

