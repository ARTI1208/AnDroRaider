# Maintainer: {@developer} <{@email}>

pkgname={@package}
appName={@name}
pkgver={@version}
pkgrel={@build}
pkgdesc="{@description}"
arch=('x86_64')

build() {
    cd {%projectRoot}

    ./gradlew jlink
}

package() {
    cd {%projectRoot}

	install -d "$pkgdir"/{@installDir}
	appInstallRoot=opt/"$pkgname"
	cp -R {%projectBuild}/image "$pkgdir"/"$appInstallRoot"

    logoDir="$pkgdir"/usr/share/pixmaps
	mkdir -p "$logoDir"
	cp {%linuxExtraResources}/"$appName".png "$logoDir"/"$appName".png

	desktopDir="$pkgdir"/usr/share/applications
	mkdir -p "$desktopDir"
    cp {%linuxExtraResources}/"$appName".desktop "$desktopDir"/"$pkgname".desktop

    execFile=/"$appInstallRoot"/bin/"$appName"

    binDir="$pkgdir"/usr/bin
    mkdir -p "$binDir"
    install {%linuxExtraResources}/"$pkgname".sh "$binDir"/"$pkgname"
}
