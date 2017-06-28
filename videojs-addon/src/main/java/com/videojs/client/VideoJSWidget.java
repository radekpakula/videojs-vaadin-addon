package com.videojs.client;

import java.util.List;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SourceElement;
import com.google.gwt.dom.client.VideoElement;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.ui.Label;

// Extend any GWT Widget
public class VideoJSWidget extends Label {
	private List<String> sources;
	private List<String> urls;
	private List<String> subtitles;
	private List<String>  subtitleUrls;
	private List<String> attributes;
	
    private String id;
	private List<String> types;
	private DivElement root;
	private VideoElement video;
	
	
	public VideoJSWidget() {
        root = Document.get().createDivElement();
        root.setClassName("media-player");
        video=Document.get().createVideoElement();
        video.addClassName("video-js vjs-default-skin");
        video.setAttribute("controls", "");
        video.setAttribute("preload", "none");
        video.setAttribute("width", "100%");
        video.setAttribute("height","100%");
        video.setAttribute("data-setup","{}");
        root.appendChild(video);
        setElement(root);
		
    }
	public native void log(String log)/*-{
		console.log(log);
	}-*/;
	public void rebuildPlayer() {
		video.setId("videojs-"+id);
		video.removeAllChildren();
		if(sources!=null && sources.size()>0){
			log("source pass");
			for(int i=0;i<sources.size();i++){
				String sourceUrl = urls.get(i);
				String type = types.get(i);
				SourceElement se = Document.get().createSourceElement();
				se.setAttribute("src", sourceUrl);
				se.setAttribute("type", type);
				video.appendChild(se);
			}
		}
		if(subtitles!=null && subtitles.size()>0){
			for(int i=0;i<subtitles.size();i++){
				Element se = Document.get().createElement("track");
				JSONObject jsonValue = JSONParser.parseStrict(subtitles.get(i)).isObject();
				if (jsonValue != null) {
					se.setAttribute("src", subtitleUrls.get(i));
					if(jsonValue.get("srcLang")!=null && jsonValue.get("label")!=null && jsonValue.get("kind")!=null){
						se.setAttribute("srclang", jsonValue.get("srcLang").toString().replaceAll("\"", ""));
						se.setAttribute("label", jsonValue.get("label").toString().replaceAll("\"", ""));
						se.setAttribute("kind", jsonValue.get("kind").toString().replaceAll("\"", ""));
						video.appendChild(se);
					}
				}
			}
		}
	}
	public void setId(String connectorId) {
		this.id = connectorId;
	}
	public void appendUrls(String url){
		urls.add(url);
	}
	public void setSubtitles(List<String> subtitles) {
		this.subtitles=subtitles;
	}
	public void setSources(List<String> sources) {
		this.sources=sources;
	}
	public void setTypes(List<String> types) {
		this.types=types;
	}
	public void setUrls(List<String> urls) {
		this.urls=urls;
	}
	public void setSubtitleUrl(List<String> subtitleUrls) {
		this.subtitleUrls=subtitleUrls;
	}
	public void setAttributes(List<String> attributes) {
		this.attributes=attributes;
	}
}