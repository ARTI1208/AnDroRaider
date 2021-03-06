# Maintainer: {@developer} <{@email}>

pkgname={@package}
appName={@name}
pkgver={@version}
pkgrel={@build}
pkgdesc="{@description}"
arch=('x86_64')

package() {
    cd {%projectRoot}

    # Copy jlink results
	install -d "$pkgdir"/{@installDir}
	appInstallRoot={@installDir}/"$pkgname"
	cp -R {%projectBuild}/image "$pkgdir"/"$appInstallRoot"

    # Copy logo to system directory
    logoDir="$pkgdir"/usr/share/pixmaps
	mkdir -p "$logoDir"
	cp {%linuxExtraResources}/"$appName".png "$logoDir"/"$appName".png

    # Copy .desktop file to system directory
	desktopDir="$pkgdir"/usr/share/applications
	mkdir -p "$desktopDir"
    cp {%linuxExtraResources}/"$appName".desktop "$desktopDir"/"$pkgname".desktop

    # Copy executable file to system bin directory
    binDir="$pkgdir"/usr/bin
    mkdir -p "$binDir"
    install {%linuxExtraResources}/"$pkgname".sh "$binDir"/"$pkgname"
}
