if (window['google'] != undefined && window['google']['loader'] != undefined) {
    if (!window['google']['visualization']) {
        window['google']['visualization'] = {};
        google.visualization.Version = '1.1';
        google.visualization.JSHash = '9543863e4f7c29aa0bc62c0051a89a8a';
        google.visualization.LoadArgs = 'file\75visualization\46v\0751.1\46packages\75sankey';
    }
    google.loader.writeLoadTag("css", google.loader.ServiceBase + "/api/visualization/1.1/9543863e4f7c29aa0bc62c0051a89a8a/ui+en.css", false);
    google.loader.writeLoadTag("script", google.loader.ServiceBase + "/api/visualization/1.1/9543863e4f7c29aa0bc62c0051a89a8a/d3,d3sankey,format+en,default+en,ui+en,sankey+en.I.js", false);
}
