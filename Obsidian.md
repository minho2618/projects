<mxfile host="app.diagrams.net" agent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36 Edg/136.0.0.0" version="26.3.0">
  <diagram id="C5RBs43oDa-KdzZeNtuy" name="Page-1">
    <mxGraphModel dx="2028" dy="3006" grid="1" gridSize="10" guides="1" tooltips="1" connect="1" arrows="1" fold="1" page="1" pageScale="1" pageWidth="827" pageHeight="1169" math="0" shadow="0">
      <root>
        <mxCell id="WIyWlLk6GJQsqaUBKTNV-0" />
        <mxCell id="WIyWlLk6GJQsqaUBKTNV-1" parent="WIyWlLk6GJQsqaUBKTNV-0" />
        <mxCell id="Rqsa_Y6ufftONVFg37X1-0" value="Reservation" style="swimlane;fontStyle=2;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeLast=0;collapsible=1;marginBottom=0;rounded=0;shadow=0;strokeWidth=1;" parent="WIyWlLk6GJQsqaUBKTNV-1" vertex="1">
          <mxGeometry x="40" y="-1220" width="160" height="238" as="geometry">
            <mxRectangle x="230" y="140" width="160" height="26" as="alternateBounds" />
          </mxGeometry>
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-19" value="- reserveCode: int" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;rounded=0;shadow=0;html=0;" parent="Rqsa_Y6ufftONVFg37X1-0" vertex="1">
          <mxGeometry y="26" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-1" value="- room: Room" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" parent="Rqsa_Y6ufftONVFg37X1-0" vertex="1">
          <mxGeometry y="52" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-2" value="- date: MyDate" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;rounded=0;shadow=0;html=0;" parent="Rqsa_Y6ufftONVFg37X1-0" vertex="1">
          <mxGeometry y="78" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-3" value="- customer: Customer" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;rounded=0;shadow=0;html=0;" parent="Rqsa_Y6ufftONVFg37X1-0" vertex="1">
          <mxGeometry y="104" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-62" value="- isBreakfast: boolean" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;rounded=0;shadow=0;html=0;" parent="Rqsa_Y6ufftONVFg37X1-0" vertex="1">
          <mxGeometry y="130" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-80" value="- people: int" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;rounded=0;shadow=0;html=0;" parent="Rqsa_Y6ufftONVFg37X1-0" vertex="1">
          <mxGeometry y="156" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-81" value="- event : Event" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;rounded=0;shadow=0;html=0;" parent="Rqsa_Y6ufftONVFg37X1-0" vertex="1">
          <mxGeometry y="182" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-6" value="Customer" style="swimlane;fontStyle=2;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeLast=0;collapsible=1;marginBottom=0;rounded=0;shadow=0;strokeWidth=1;" parent="WIyWlLk6GJQsqaUBKTNV-1" vertex="1">
          <mxGeometry x="310" y="-1160" width="160" height="138" as="geometry">
            <mxRectangle x="230" y="140" width="160" height="26" as="alternateBounds" />
          </mxGeometry>
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-7" value="- name:  String" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" parent="Rqsa_Y6ufftONVFg37X1-6" vertex="1">
          <mxGeometry y="26" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-9" value="- gender: char" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;rounded=0;shadow=0;html=0;" parent="Rqsa_Y6ufftONVFg37X1-6" vertex="1">
          <mxGeometry y="52" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-8" value="- phoneNumber: String" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;rounded=0;shadow=0;html=0;" parent="Rqsa_Y6ufftONVFg37X1-6" vertex="1">
          <mxGeometry y="78" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-12" value="Room" style="swimlane;fontStyle=2;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeLast=0;collapsible=1;marginBottom=0;rounded=0;shadow=0;strokeWidth=1;" parent="WIyWlLk6GJQsqaUBKTNV-1" vertex="1">
          <mxGeometry x="37.5" y="-920" width="165" height="326" as="geometry">
            <mxRectangle x="230" y="140" width="160" height="26" as="alternateBounds" />
          </mxGeometry>
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-40" value="static final checkIn: Time" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-12">
          <mxGeometry y="26" width="165" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-41" value="static final checkOut: Time" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-12">
          <mxGeometry y="56" width="165" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-28" value="- name: String" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-12">
          <mxGeometry y="86" width="165" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-29" value="- price: Double" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-12">
          <mxGeometry y="116" width="165" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-30" value="- info: String" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-12">
          <mxGeometry y="146" width="165" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-33" value="- gender: char" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-12">
          <mxGeometry y="176" width="165" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-35" value="- maxCount: int" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-12">
          <mxGeometry y="206" width="165" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-37" value="- minCount: int" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-12">
          <mxGeometry y="236" width="165" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-39" value="- furniture: String" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-12">
          <mxGeometry y="266" width="165" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-42" value="- isSoldOut: boolean" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-12">
          <mxGeometry y="296" width="165" height="30" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-21" value="Has a" style="endArrow=classic;html=1;rounded=0;entryX=1;entryY=0.5;entryDx=0;entryDy=0;exitX=-0.009;exitY=0.198;exitDx=0;exitDy=0;exitPerimeter=0;" parent="WIyWlLk6GJQsqaUBKTNV-1" source="Rqsa_Y6ufftONVFg37X1-9" target="Rqsa_Y6ufftONVFg37X1-3" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="290" y="-1086" as="sourcePoint" />
            <mxPoint x="200" y="-1104" as="targetPoint" />
            <Array as="points" />
          </mxGeometry>
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-23" value="Has a" style="endArrow=classic;html=1;rounded=0;entryX=0.5;entryY=1;entryDx=0;entryDy=0;exitX=0.5;exitY=0;exitDx=0;exitDy=0;" parent="WIyWlLk6GJQsqaUBKTNV-1" source="Rqsa_Y6ufftONVFg37X1-12" target="Rqsa_Y6ufftONVFg37X1-0" edge="1">
          <mxGeometry width="50" height="50" relative="1" as="geometry">
            <mxPoint x="290" y="-1045" as="sourcePoint" />
            <mxPoint x="210" y="-1045" as="targetPoint" />
          </mxGeometry>
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-36" value="Has a" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;entryX=0;entryY=0.5;entryDx=0;entryDy=0;" parent="WIyWlLk6GJQsqaUBKTNV-1" source="Rqsa_Y6ufftONVFg37X1-24" target="Rqsa_Y6ufftONVFg37X1-2" edge="1">
          <mxGeometry relative="1" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-24" value="MyDate" style="swimlane;fontStyle=2;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeLast=0;collapsible=1;marginBottom=0;rounded=0;shadow=0;strokeWidth=1;" parent="WIyWlLk6GJQsqaUBKTNV-1" vertex="1">
          <mxGeometry x="-230" y="-1083" width="160" height="108" as="geometry">
            <mxRectangle x="230" y="140" width="160" height="26" as="alternateBounds" />
          </mxGeometry>
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-25" value="- year: int" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" parent="Rqsa_Y6ufftONVFg37X1-24" vertex="1">
          <mxGeometry y="26" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-26" value="- month: int" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;rounded=0;shadow=0;html=0;" parent="Rqsa_Y6ufftONVFg37X1-24" vertex="1">
          <mxGeometry y="52" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-27" value="- day: int" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;rounded=0;shadow=0;html=0;" parent="Rqsa_Y6ufftONVFg37X1-24" vertex="1">
          <mxGeometry y="78" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-51" value="MyTime" style="swimlane;fontStyle=2;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeLast=0;collapsible=1;marginBottom=0;rounded=0;shadow=0;strokeWidth=1;" parent="WIyWlLk6GJQsqaUBKTNV-1" vertex="1">
          <mxGeometry x="-230" y="-842" width="160" height="104" as="geometry">
            <mxRectangle x="230" y="140" width="160" height="26" as="alternateBounds" />
          </mxGeometry>
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-52" value="- hour: int" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" parent="Rqsa_Y6ufftONVFg37X1-51" vertex="1">
          <mxGeometry y="26" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-53" value="- minute: int" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;rounded=0;shadow=0;html=0;" parent="Rqsa_Y6ufftONVFg37X1-51" vertex="1">
          <mxGeometry y="52" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-54" value="- second: int" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;rounded=0;shadow=0;html=0;" parent="Rqsa_Y6ufftONVFg37X1-51" vertex="1">
          <mxGeometry y="78" width="160" height="26" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-79" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;entryX=0;entryY=0.5;entryDx=0;entryDy=0;exitX=-0.025;exitY=0.552;exitDx=0;exitDy=0;exitPerimeter=0;" parent="WIyWlLk6GJQsqaUBKTNV-1" source="Rqsa_Y6ufftONVFg37X1-72" target="Rqsa_Y6ufftONVFg37X1-19" edge="1">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="-130" y="-588" as="sourcePoint" />
            <mxPoint x="-90" y="-960" as="targetPoint" />
            <Array as="points">
              <mxPoint x="-300" y="-475" />
              <mxPoint x="-300" y="-1181" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-7" value="Has a" style="edgeLabel;html=1;align=center;verticalAlign=middle;resizable=0;points=[];" vertex="1" connectable="0" parent="Rqsa_Y6ufftONVFg37X1-79">
          <mxGeometry x="-0.0496" y="4" relative="1" as="geometry">
            <mxPoint as="offset" />
          </mxGeometry>
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-69" value="Event" style="swimlane;fontStyle=2;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeLast=0;collapsible=1;marginBottom=0;rounded=0;shadow=0;strokeWidth=1;" parent="WIyWlLk6GJQsqaUBKTNV-1" vertex="1">
          <mxGeometry x="40" y="-548" width="160" height="236" as="geometry">
            <mxRectangle x="230" y="140" width="160" height="26" as="alternateBounds" />
          </mxGeometry>
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-73" value="static final minCount : int&amp;nbsp;" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" parent="Rqsa_Y6ufftONVFg37X1-69" vertex="1">
          <mxGeometry y="26" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-72" value="&lt;div style=&quot;&quot;&gt;&lt;span style=&quot;background-color: transparent; color: light-dark(rgb(0, 0, 0), rgb(255, 255, 255));&quot;&gt;static final maxCount : int&lt;/span&gt;&lt;/div&gt;" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" parent="Rqsa_Y6ufftONVFg37X1-69" vertex="1">
          <mxGeometry y="56" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-0" value="- price : int" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-69">
          <mxGeometry y="86" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-1" value="- eventType : String" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-69">
          <mxGeometry y="116" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-15" value="- startTime:Time" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-69">
          <mxGeometry y="146" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-16" value="- endTime:Time" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-69">
          <mxGeometry y="176" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-52" value="doEvent()" style="text;html=1;align=left;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-69">
          <mxGeometry y="206" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-77" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;entryX=0.5;entryY=1;entryDx=0;entryDy=0;" parent="WIyWlLk6GJQsqaUBKTNV-1" source="Rqsa_Y6ufftONVFg37X1-70" target="Rqsa_Y6ufftONVFg37X1-69" edge="1">
          <mxGeometry relative="1" as="geometry">
            <Array as="points">
              <mxPoint x="-60" y="-228" />
              <mxPoint x="120" y="-228" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-70" value="PartyEvent" style="swimlane;fontStyle=2;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeLast=0;collapsible=1;marginBottom=0;rounded=0;shadow=0;strokeWidth=1;" parent="WIyWlLk6GJQsqaUBKTNV-1" vertex="1">
          <mxGeometry x="-150" y="-199.99999999999994" width="160" height="138" as="geometry">
            <mxRectangle x="230" y="140" width="160" height="26" as="alternateBounds" />
          </mxGeometry>
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-10" value="type: String" style="text;html=1;align=center;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-70">
          <mxGeometry y="26" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-13" value="food: String" style="text;html=1;align=center;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-70">
          <mxGeometry y="56" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-54" value="doEvent(){}" style="text;html=1;align=center;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-70">
          <mxGeometry y="86" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-76" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;entryX=0.5;entryY=1;entryDx=0;entryDy=0;" parent="WIyWlLk6GJQsqaUBKTNV-1" source="Rqsa_Y6ufftONVFg37X1-74" target="Rqsa_Y6ufftONVFg37X1-69" edge="1">
          <mxGeometry relative="1" as="geometry">
            <Array as="points">
              <mxPoint x="120" y="-250" />
              <mxPoint x="120" y="-250" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-74" value="BBQEvent" style="swimlane;fontStyle=2;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeLast=0;collapsible=1;marginBottom=0;rounded=0;shadow=0;strokeWidth=1;" parent="WIyWlLk6GJQsqaUBKTNV-1" vertex="1">
          <mxGeometry x="40" y="-199.99999999999994" width="160" height="138" as="geometry">
            <mxRectangle x="230" y="140" width="160" height="26" as="alternateBounds" />
          </mxGeometry>
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-11" value="equipment : String" style="text;html=1;align=center;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-74">
          <mxGeometry y="26" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-17" value="food : String" style="text;html=1;align=center;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-74">
          <mxGeometry y="56" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-55" value="doEvent(){}" style="text;html=1;align=center;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-74">
          <mxGeometry y="86" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-78" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;entryX=0.5;entryY=1;entryDx=0;entryDy=0;" parent="WIyWlLk6GJQsqaUBKTNV-1" source="Rqsa_Y6ufftONVFg37X1-75" target="Rqsa_Y6ufftONVFg37X1-69" edge="1">
          <mxGeometry relative="1" as="geometry">
            <Array as="points">
              <mxPoint x="324" y="-228" />
              <mxPoint x="120" y="-228" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="Rqsa_Y6ufftONVFg37X1-75" value="FishingEvent" style="swimlane;fontStyle=2;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeLast=0;collapsible=1;marginBottom=0;rounded=0;shadow=0;strokeWidth=1;" parent="WIyWlLk6GJQsqaUBKTNV-1" vertex="1">
          <mxGeometry x="244" y="-199.99999999999994" width="160" height="138" as="geometry">
            <mxRectangle x="230" y="140" width="160" height="26" as="alternateBounds" />
          </mxGeometry>
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-23" value="isBoat: boolean" style="text;html=1;align=center;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-75">
          <mxGeometry y="26" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-18" value="area: String" style="text;html=1;align=center;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-75">
          <mxGeometry y="56" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-56" value="doEvent(){}" style="text;html=1;align=center;verticalAlign=middle;resizable=0;points=[];autosize=1;strokeColor=none;fillColor=none;" vertex="1" parent="Rqsa_Y6ufftONVFg37X1-75">
          <mxGeometry y="86" width="160" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-26" value="abstract" style="text;html=1;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="WIyWlLk6GJQsqaUBKTNV-1">
          <mxGeometry x="90" y="-578" width="60" height="30" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-44" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;exitX=0.5;exitY=0;exitDx=0;exitDy=0;entryX=-0.006;entryY=0.533;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="WIyWlLk6GJQsqaUBKTNV-1" source="Rqsa_Y6ufftONVFg37X1-51" target="JdRP1rSg128GCIf9JqEc-40">
          <mxGeometry relative="1" as="geometry" />
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-45" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;exitX=0.43;exitY=1.11;exitDx=0;exitDy=0;exitPerimeter=0;entryX=-0.021;entryY=0.429;entryDx=0;entryDy=0;entryPerimeter=0;" edge="1" parent="WIyWlLk6GJQsqaUBKTNV-1" source="Rqsa_Y6ufftONVFg37X1-54" target="JdRP1rSg128GCIf9JqEc-16">
          <mxGeometry relative="1" as="geometry">
            <mxPoint x="-210" y="-750" as="sourcePoint" />
            <mxPoint x="20" y="-340" as="targetPoint" />
            <Array as="points">
              <mxPoint x="-161" y="-359" />
            </Array>
          </mxGeometry>
        </mxCell>
        <mxCell id="JdRP1rSg128GCIf9JqEc-46" value="&lt;b&gt;추상메서드 x&lt;/b&gt;" style="text;html=1;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;" vertex="1" parent="WIyWlLk6GJQsqaUBKTNV-1">
          <mxGeometry x="202.5" y="-550" width="90" height="30" as="geometry" />
        </mxCell>
      </root>
    </mxGraphModel>
  </diagram>
</mxfile>
