( function ( $ ) {
    "use strict";
    jQuery( '#vmap' ).vectorMap( {
        map: 'world_en',
        backgroundColor: null,
        color: '#ffffff',
        hoverOpacity: 0.7,
        selectedColor: '#1de9b6',
        enableZoom: true,
        showTooltip: true,
        values: sample_data,
        scaleColors: [ '#1de9b6', '#03a9f5' ],
        normalizeFunction: 'polynomial'
    } );
})(jQuery);

// var map;
//
// jQuery( document ).ready( function () {
//
//     // Store currentRegion
//     var currentRegion = 'fl';
//
//     // List of Regions we'll let clicks through for
//     var enabledRegions = [ 'mo', 'fl', 'or' ];
//
//     map = jQuery( '#vmap15' ).vectorMap( {
//         map: 'usa_en',
//         color: '#007BFF',
//         borderColor: '#fff',
//         backgroundColor: '#fff',
//         enableZoom: true,
//         showTooltip: true,
//         selectedColor: '#001BFF',
//         selectedRegions: [ 'fl' ],
//         hoverColor: null,
//         colors: {
//             mo: '#001BFF',
//             fl: '#001BFF',
//             or: '#001BFF'
//         },
//         onRegionClick: function ( event, code, region ) {
//             // Check if this is an Enabled Region, and not the current selected on
//             if ( enabledRegions.indexOf( code ) === -1 || currentRegion === code ) {
//                 // Not an Enabled Region
//                 event.preventDefault();
//             } else {
//                 // Enabled Region. Update Newly Selected Region.
//                 currentRegion = code;
//             }
//         },
//         onRegionSelect: function ( event, code, region ) {
//             console.log( map.selectedRegions );
//         },
//         onLabelShow: function ( event, label, code ) {
//             if ( enabledRegions.indexOf( code ) === -1 ) {
//                 event.preventDefault();
//             }
//         }
//     } );
// } );