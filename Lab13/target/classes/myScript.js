
var length=0;
function setshort(){
length=0;
}
function setlong(){
    length=1;
}
function birthday(){
    var name=nameField.getText();
    var yourName=yourNameField.getText();
    var shortWishes="Z okazji Twoich urodzin życzę Ci, wszelkiej pomyślności! Spełnienia wszystkich najksrytszych marzeń oraz byś zawsze miał wszystkiego czego Ci potrzeba! \n"
    var longWishes="W tym szczególnym dniu życzę Ci aby miłośc i szczęście zagościły na stały w Twoim życiu \n Niech każda spędzona chwila niesie za sobą dziecięcą radość, moc wrażeń i spełnienie \n Oby ten dzień urodzin uświadomił Ci jak wiele wspaniałych osób masz obok siebie, którzy darzą Cię ogromem miłości \n Wszystkiego najlepszego w dniu Twoich urodzin! \n"
    if(length==0){
       if(name=="Maciek" || name=="maciek"){
           wishes.setText("Drogi Maćku!\n "+shortWishes+yourName)
       }
       if(name=="Tomek" || name=="tomek"){
        wishes.setText("Drogi Tomku!\n "+shortWishes+yourName)
    }
    if(name=="Piotr" || name=="piotr"){
        wishes.setText("Drogi Piotrku!\n "+shortWishes+yourName)
    }
    if(name=="Adam" || name=="adam"){
        wishes.setText("Drogi Adamie!\n "+shortWishes+yourName)
    }
    if(name=="Ania" || name=="ania"){
        wishes.setText("Droga Aniu!\n "+shortWishes+yourName)
    }
    if(name=="Kasia" || name=="kasia"){
        wishes.setText("Droga Kasiu!\n "+shortWishes+yourName)
    }
    if(name=="Maja" || name=="maja"){
        wishes.setText("Droga Maju!\n "+shortWishes+yourName)
    }
    if(name=="Magda" || name=="magda"){
        wishes.setText("Droga Magdo!\n "+shortWishes+yourName)
    }
      
    }
    if(length==1){
        if(name=="Maciek" || name=="maciek"){
            wishes.setText("Drogi Maćku!\n "+longWishes+yourName)
        }
        if(name=="Tomek" || name=="tomek"){
         wishes.setText("Drogi Tomku!\n "+longWishes+yourName)
     }
     if(name=="Piotr" || name=="piotr"){
         wishes.setText("Drogi Piotrku!\n "+longWishes+yourName)
     }
     if(name=="Adam" || name=="adam"){
         wishes.setText("Drogi Adamie!\n "+longWishes+yourName)
     }
     if(name=="Ania" || name=="ania"){
         wishes.setText("Droga Aniu!\n "+longWishes+yourName)
     }
     if(name=="Kasia" || name=="kasia"){
         wishes.setText("Droga Kasiu!\n "+longWishes+yourName)
     }
     if(name=="Maja" || name=="maja"){
         wishes.setText("Droga Maju!\n "+longWishes+yourName)
     }
     if(name=="Magda" || name=="magda"){
         wishes.setText("Droga Magdo!\n "+longWishes+yourName)
     }
    }

}
function newYear(){
    var name=nameField.getText();
    var yourName=yourNameField.getText();
    var shortWishes="Na wszystkie dni Nowego Roku \n Życzę Ci wiary w sercu i światła w mroku \n Obyś jednym krokiem mijał przeszkody \n Byś czuł się silny i wiecznie młody! \n"
    var longWishes="O tej porze każdy sobie życzy, co tylko może. \n Bliscy bliskim zdrowia, miłości, radości, nie tylko od święta, ale na co dzień bliskości. \n A ja dołożę do tego jeszcze, serdecznych spotkań rodzinnych, pukania do drzwi, przyjaznych twarzy, dużo radości, serdecznych objęć, pocałunków, szczęśliwych wspomnień. \n"
    if(length==0){
       if(name=="Maciek" || name=="maciek"){
           wishes.setText("Drogi Maćku!\n "+shortWishes+yourName)
       }
       if(name=="Tomek" || name=="tomek"){
        wishes.setText("Drogi Tomku!\n "+shortWishes+yourName)
    }
    if(name=="Piotr" || name=="piotr"){
        wishes.setText("Drogi Piotrku!\n "+shortWishes+yourName)
    }
    if(name=="Adam" || name=="adam"){
        wishes.setText("Drogi Adamie!\n "+shortWishes+yourName)
    }
    if(name=="Ania" || name=="ania"){
        wishes.setText("Droga Aniu!\n "+shortWishes+yourName)
    }
    if(name=="Kasia" || name=="kasia"){
        wishes.setText("Droga Kasiu!\n "+shortWishes+yourName)
    }
    if(name=="Maja" || name=="maja"){
        wishes.setText("Droga Maju!\n "+shortWishes+yourName)
    }
    if(name=="Magda" || name=="magda"){
        wishes.setText("Droga Magdo!\n "+shortWishes+yourName)
    }
      
    }
    if(length==1){
        if(name=="Maciek" || name=="maciek"){
            wishes.setText("Drogi Maćku!\n "+longWishes+yourName)
        }
        if(name=="Tomek" || name=="tomek"){
         wishes.setText("Drogi Tomku!\n "+longWishes+yourName)
     }
     if(name=="Piotr" || name=="piotr"){
         wishes.setText("Drogi Piotrku!\n "+longWishes+yourName)
     }
     if(name=="Adam" || name=="adam"){
         wishes.setText("Drogi Adamie!\n "+longWishes+yourName)
     }
     if(name=="Ania" || name=="ania"){
         wishes.setText("Droga Aniu!\n "+longWishes+yourName)
     }
     if(name=="Kasia" || name=="kasia"){
         wishes.setText("Droga Kasiu!\n "+longWishes+yourName)
     }
     if(name=="Maja" || name=="maja"){
         wishes.setText("Droga Maju!\n "+longWishes+yourName)
     }
     if(name=="Magda" || name=="magda"){
         wishes.setText("Droga Magdo!\n "+longWishes+yourName)
     }
    }
}
function graduation(){
    var name=nameField.getText();
    var yourName=yourNameField.getText();
    var shortWishes="Z okazji obrony dyplomu, życzę Ci wielu sukcesów i satysfakcji \n wspaniałej kariery i wiele osiągnięć \n a także powodzenia w dalszej drodze edukacyjnej \n"
    var longWishes="Z okazji obrony pracy \n życzę Ci,  \n cieszenia się sukcesami, \n umiejętności wyciągania wnioski z porażek. \n Życzę Ci szczęścia, radości i osiągnięcia \n wszystkich zamierzonych celów. \n Pamiętaj: Każdego dnia możesz zrobić coś, \n aby zbliżyć się do swojego marzenia \n lub możesz nie robić niczego. \n W każdym przypadku podejmujesz decyzję \n"
    if(length==0){
       if(name=="Maciek" || name=="maciek"){
           wishes.setText("Drogi Maćku!\n "+shortWishes+yourName)
       }
       if(name=="Tomek" || name=="tomek"){
        wishes.setText("Drogi Tomku!\n "+shortWishes+yourName)
    }
    if(name=="Piotr" || name=="piotr"){
        wishes.setText("Drogi Piotrku!\n "+shortWishes+yourName)
    }
    if(name=="Adam" || name=="adam"){
        wishes.setText("Drogi Adamie!\n "+shortWishes+yourName)
    }
    if(name=="Ania" || name=="ania"){
        wishes.setText("Droga Aniu!\n "+shortWishes+yourName)
    }
    if(name=="Kasia" || name=="kasia"){
        wishes.setText("Droga Kasiu!\n "+shortWishes+yourName)
    }
    if(name=="Maja" || name=="maja"){
        wishes.setText("Droga Maju!\n "+shortWishes+yourName)
    }
    if(name=="Magda" || name=="magda"){
        wishes.setText("Droga Magdo!\n "+shortWishes+yourName)
    }
      
    }
    if(length==1){
        if(name=="Maciek" || name=="maciek"){
            wishes.setText("Drogi Maćku!\n "+longWishes+yourName)
        }
        if(name=="Tomek" || name=="tomek"){
         wishes.setText("Drogi Tomku!\n "+longWishes+yourName)
     }
     if(name=="Piotr" || name=="piotr"){
         wishes.setText("Drogi Piotrku!\n "+longWishes+yourName)
     }
     if(name=="Adam" || name=="adam"){
         wishes.setText("Drogi Adamie!\n "+longWishes+yourName)
     }
     if(name=="Ania" || name=="ania"){
         wishes.setText("Droga Aniu!\n "+longWishes+yourName)
     }
     if(name=="Kasia" || name=="kasia"){
         wishes.setText("Droga Kasiu!\n "+longWishes+yourName)
     }
     if(name=="Maja" || name=="maja"){
         wishes.setText("Droga Maju!\n "+longWishes+yourName)
     }
     if(name=="Magda" || name=="magda"){
         wishes.setText("Droga Magdo!\n "+longWishes+yourName)
     }
    }
}