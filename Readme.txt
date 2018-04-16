Different commands to be used

mvn clean install -Dsource.path=src/main/resources/en.json -Dtarget.language=de -Dtarget.path=C:\Users\harishv\Desktop\de.json -Dregenerate=true

mvn clean install -Dsource.path=C:\Users\harishv\Desktop\en.json -Dtarget.language=de -Dtarget.path=C:\Users\harishv\Desktop\de.json -Dregenerate=true

mvn clean install -Dsource.path=C:\Users\harishv\Desktop\en.json -Dtarget.language=de -Dtarget.path=C:\Users\harishv\Desktop\de.json

mvn clean install -Dsource.path=C:\Users\harishv\Desktop\en.json -Dtarget.language=de -Dregenerate=true

mvn clean install -Dsource.path=C:\Users\harishv\Desktop\en.json -Dtarget.language=de

Sample localisation file en.json is available in resources path

Supported Language keywords for google translator
{"af","sq","am","ar","hy","az","eu","be","bn","bs","bg","ca","zh-CN","zh-TW","co","hr","cs","da","nl","eo","et","fi",
"fr","fy","gl","ka","de","el","gu","ht","ha","haw","iw","hi","hmn","hu","is","ig","id","ga","it","ja","jw","kn","kk","km",
"ko","ku","ky","lo","la","lv","lt","lb","mk","mg","ms","ml","mt","mi","mr","mn","my","ne","no","ny","ps","fa","pl","pt","pa",
"ro","ru","sm","gd","sr","st","sn","sd","si","sk","sl","so","es","su","sw","sv","tl","tg","ta","te","th","tr","uk","ur","uz","vi",
"cy","xh","yi","yo","zu"}